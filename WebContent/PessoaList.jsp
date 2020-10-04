<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Livraria</title>
</head>
<body>
	<center>
		<h1>Gerenciamento de Clientes</h1>
		<h2>
			<a href="/Livraria/pessoa/new">Novo Cliente</a> &nbsp;&nbsp;&nbsp; <a
				href="/Livraria/pessoa/list">Listar Clientes</a>

		</h2>
	</center>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>Clientes</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>nome</th>
				<th>endereco</th>
				<th>Ações</th>
			</tr>
			<c:forEach var="pess" items="${listPessoa}">
				<tr>
					<td><c:out value="${pess.id}" /></td>
					<td><c:out value="${pess.nome}" /></td>
					<td><c:out value="${pess.endereco}" /></td>
					<td>
					<a href="/Livraria/pessoa/edit?id=<c:out value='${pess.id}' />">Editar</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/Livraria/pessoa/delete?id=<c:out value='${pess.id}' />">Deletar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
