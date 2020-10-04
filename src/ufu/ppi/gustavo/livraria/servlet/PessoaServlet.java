package ufu.ppi.gustavo.livraria.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufu.ppi.gustavo.livraria.DAO.PessoaDAO;
import ufu.ppi.gustavo.livraria.DTO.Pessoa;

public class PessoaServlet extends HttpServlet {
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
				insertPessoa(request, response);
				break;
			case "/delete":
				deletePessoa(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updatePessoa(request, response);
				break;
			default:
				listPessoa(request, response);
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

	private void listPessoa(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Pessoa> listPessoa = PessoaDAO.listAllPessoas();
		request.setAttribute("listPessoa", listPessoa);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PessoaList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/FormPessoa.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		if (id > 0) {
			Pessoa pessoa = PessoaDAO.getPessoa(id);
			request.setAttribute("pessoa", pessoa);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/FormPessoa.jsp");
			dispatcher.forward(request, response);
		} else {
			showNewForm(request, response);
		}
	}

	private void insertPessoa(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String num_cartao = request.getParameter("num_cartao");
		String cvv = request.getParameter("cvv");
		int validade_cartao = Integer.parseInt(request.getParameter("validade_cartao"));

		Pessoa newPessoa = new Pessoa(nome, endereco, num_cartao, cvv, validade_cartao);
		PessoaDAO.insertPessoa(newPessoa);
		response.sendRedirect("/Livraria/pessoa/list");
	}

	private void updatePessoa(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String num_cartao = request.getParameter("num_cartao");
		String cvv = request.getParameter("cvv");
		int validade_cartao = Integer.parseInt(request.getParameter("validade_cartao"));

		Pessoa pess = new Pessoa(id, nome, endereco, num_cartao, cvv, validade_cartao);

		PessoaDAO.updatePessoa(pess);
		response.sendRedirect("/Livraria/pessoa/list");
	}

	private void deletePessoa(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Pessoa pess = new Pessoa(id);
		PessoaDAO.deletePessoa(pess);

		response.sendRedirect("/Livraria/pessoa/list");
	}

}
