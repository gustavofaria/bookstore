<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Livraria</title>
</head>
<body>
	<center>
		<h1>Gerenciamento de Gêneros Literários</h1>
		<h2>
			<a href="/Livraria/genero/new">Novo Gênero</a> &nbsp;&nbsp;&nbsp; <a
				href="/Livraria/genero/list">Listar Gêneros</a>

		</h2>
	</center>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>Gêneros Literários</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Descricao</th>
				<th>Ações</th>
			</tr>
			<c:forEach var="gen" items="${listGenero}">
				<tr>
					<td><c:out value="${gen.id}" /></td>
					<td><c:out value="${gen.descricao}" /></td>
					<td>
					<a href="/Livraria/genero/edit?id=<c:out value='${gen.id}' />">Editar</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/Livraria/genero/delete?id=<c:out value='${gen.id}' />">Deletar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
