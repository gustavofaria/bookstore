package ufu.ppi.gustavo.livraria.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufu.ppi.gustavo.livraria.DAO.CompraDAO;
import ufu.ppi.gustavo.livraria.DTO.Compra;

public class CompraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		if (action == null)
			action = "/Livraria/pessoa/list";
		action = action.replace("/Livraria/pessoa", "");
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				realizaCompra(request, response);
				break;
			case "/delete":
				deleteCompra(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateCompra(request, response);
				break;
			default:
				listCompra(request, response);
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

	private void listCompra(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Compra> listCompra = CompraDAO.listAllCompras();
		request.setAttribute("listCompra", listCompra);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CompraList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/FormCompra.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		if (id > 0) {
			Compra compra = CompraDAO.getCompra(id);
			request.setAttribute("compra", compra);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/FormCompra.jsp");
			dispatcher.forward(request, response);
		} else {
			showNewForm(request, response);
		}
	}

	private void realizaCompra(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		int pessoa_id = Integer.parseInt(request.getParameter("pessoa_id"));
		int livro_id = Integer.parseInt(request.getParameter("livro_id"));
		int quantidade = Integer.parseInt(request.getParameter("quantidade"));

		Compra newCompra = new Compra(pessoa_id, livro_id, quantidade);
		CompraDAO.insertCompra(newCompra);
		response.sendRedirect("/Livraria/compra/list");
	}

	private void updateCompra(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		int compra_id = Integer.parseInt(request.getParameter("id"));
		int pessoa_id = Integer.parseInt(request.getParameter("pessoa_id"));
		int livro_id = Integer.parseInt(request.getParameter("livro_id"));
		int quantidade = Integer.parseInt(request.getParameter("quantidade"));

		Compra compra = new Compra(compra_id, pessoa_id, livro_id, quantidade);
		CompraDAO.updatePessoa(compra);
		response.sendRedirect("/Livraria/compra/list");
	}

	private void deleteCompra(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Compra compra = new Compra(id);
		CompraDAO.deleteCompra(compra);

		response.sendRedirect("/Livraria/compra/list");
	}

}
