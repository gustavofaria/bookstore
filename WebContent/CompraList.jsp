<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Livraria</title>
</head>
<body>
	<center>
		<h1>Gerenciamento de Compras</h1>
		<h2>
			<a href="/Livraria/compra/new">Nova Compra</a> &nbsp;&nbsp;&nbsp; <a
				href="/Livraria/compra/list">Listar Compras</a>

		</h2>
	</center>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>Compras</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Cliente</th>
				<th>Livro</th>
				<th>Quantidade</th>
				<th>Ações</th>
			</tr>
			<c:forEach var="compra" items="${listCompra}">
				<tr>
					<td><c:out value="${compra.id}" /></td>
					<td><c:out value="${compra.pessoa_id}" /></td>
					<td><c:out value="${compra.livro_id}" /></td>
					<td><c:out value="${compra.quantidade}" /></td>
					<td><a style="pointer-events: none;"
						href="/Livraria/compra/edit?id=<c:out value='${compra.id}' />">Editar</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a style="pointer-events: none;"
						href="/Livraria/compra/delete?id=<c:out value='${compra.id}' />">Deletar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
