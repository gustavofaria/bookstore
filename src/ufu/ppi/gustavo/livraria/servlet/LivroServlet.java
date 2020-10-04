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
import ufu.ppi.gustavo.livraria.DAO.LivroDAO;
import ufu.ppi.gustavo.livraria.DAO.Livro_GeneroDAO;
import ufu.ppi.gustavo.livraria.DTO.Genero;
import ufu.ppi.gustavo.livraria.DTO.Livro;
import ufu.ppi.gustavo.livraria.DTO.Livro_edit;

public class LivroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		String action = request.getPathInfo();
		if(action == null) action = "/Livraria/livro/list";
		action = action.replace("/Livraria/livro", "");
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertBook(request, response);
				break;
			case "/delete":
				deleteBook(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateBook(request, response);
				break;
			default:
				listBook(request, response);
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

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		if (request.getParameter("id") != null && Integer.parseInt(request.getParameter("id")) > 0) {
		showEditForm(request, response);
		} else {
			List<Genero> todos = GeneroDAO.listAllGeneros();
			request.setAttribute("outrosGeneros", todos);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/BookForm.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		if (request.getParameter("id") != null && Integer.parseInt(request.getParameter("id")) > 0) {
			int id = Integer.parseInt(request.getParameter("id"));
			Livro_edit existingBook = Livro_GeneroDAO.getBook(id);

			List<Genero> todos = GeneroDAO.listAllGeneros();
			todos.removeAll(existingBook.getGenero());

			request.setAttribute("book", existingBook);
			request.setAttribute("outrosGeneros", todos);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/BookForm.jsp");
			dispatcher.forward(request, response);
		} else {
			showNewForm(request, response);
		}
	}

	private void listBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Livro> listBook = LivroDAO.listAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BookList.jsp");
		dispatcher.forward(request, response);
	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String title = request.getParameter("titulo");
		String author = request.getParameter("autor");
		float price = Float.parseFloat(request.getParameter("preco"));

		Livro newBook = new Livro(title, author, price);
		LivroDAO.insertBook(newBook);
		response.sendRedirect("/Livraria/livro");
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("titulo");
		String author = request.getParameter("autor");
		float price = 0f;
		if (request.getParameter("preco") != null) {
			price = Float.parseFloat(request.getParameter("preco"));
		}

		Livro_edit livro_edit = new Livro_edit(id, title, author, price);
		String[] generostr = request.getParameterValues("genero");
		if (generostr != null) {
			for (String gen_id : generostr) {
				Genero gen = new Genero(Integer.valueOf(gen_id), "");
				livro_edit.appendGenero(gen);
			}
		}
		Livro_GeneroDAO.updateLivro(livro_edit);
		response.sendRedirect("/Livraria/livro");
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Livro livro = new Livro(id);
		LivroDAO.deleteBook(livro);
		response.sendRedirect("/Livraria/livro");

	}

}
