<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Livraria</title>
</head>
<body>
	<center>
		<h1>Gerenciamento de Livros</h1>
        <h2>
        	<a href="/Livraria/livro/new">Novo Livro</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="/Livraria/livro/list">Listar Livros</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Books</h2></caption>
            <tr>
                <th>ID</th>
                <th>Titulo</th>
                <th>Autor</th>
                <th>Preco</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.id}" /></td>
                    <td><c:out value="${book.titulo}" /></td>
                    <td><c:out value="${book.autor}" /></td>
                    <td><c:out value="${book.preco}" /></td>
                    <td>
                    	<a href="/Livraria/livro/edit?id=<c:out value='${book.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="/Livraria/livro/delete?id=<c:out value='${book.id}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
