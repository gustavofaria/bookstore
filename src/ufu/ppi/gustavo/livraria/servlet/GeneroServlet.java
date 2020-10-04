package ufu.ppi.gustavo.livraria.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufu.ppi.gustavo.livraria.DAO.GeneroDAO;
import ufu.ppi.gustavo.livraria.DTO.Genero;

public class GeneroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		if(action == null) action = "/Livraria/genero/list";
		action = action.replace("/Livraria/genero", "");
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertGenero(request, response);
				break;
			case "/delete":
				deleteGenero(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateGenero(request, response);
				break;
			default:
				listGenero(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	private void listGenero(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Genero> listBook = GeneroDAO.listAllGeneros();
		request.setAttribute("listGenero", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/GeneroList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/FormGenero.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		if (id > 0) {
			Genero genero = GeneroDAO.getGenero(id);
			request.setAttribute("genero", genero);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/FormGenero.jsp");
			dispatcher.forward(request, response);
		} else {
			showNewForm(request, response);
		}
	}

	private void insertGenero(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String descricao = request.getParameter("descricao");

		Genero newGenero = new Genero(descricao);
		GeneroDAO.insertGenero(newGenero);
		response.sendRedirect("/Livraria/genero/list");
	}

	private void updateGenero(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String descricao = request.getParameter("descricao");
		Genero gen = new Genero(id, descricao);
		GeneroDAO.updateGenero(gen);
	}

	private void deleteGenero(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Genero gen = new Genero(id);
		boolean deleted = GeneroDAO.deleteGenero(gen);
		if (deleted) {

			response.sendRedirect("/Livraria/genero/list");
		} else {
			response.sendError(403, "Erro: a tentativa de deletar o gênero " + id + " falhou."
					+ " Verifique requisitos de integridade");
		}
	}

}
