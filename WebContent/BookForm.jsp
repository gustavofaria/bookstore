<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<c:if test="${book != null}">
			<form action="update" method="post">
		</c:if>
		<c:if test="${book == null}">
			<form action="insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${book != null}">
            			Edit Book
            		</c:if>
					<c:if test="${book == null}">
            			Novo Livro
            		</c:if>
				</h2>
			</caption>
			<c:if test="${book != null}">
				<input type="hidden" name="id" value="<c:out value='${book.id}' />" />
			</c:if>
			<tr>
				<th>Titulo:</th>
				<td><input type="text" name="titulo" size="45"
					value="<c:out value='${book.titulo}' />" /></td>
			</tr>
			<tr>
				<th>Autor:</th>
				<td><input type="text" name="autor" size="45"
					value="<c:out value='${book.autor}' />" /></td>
			</tr>
			<tr>
				<th>Preco:</th>
				<td><input type="text" name="preco" size="5"
					value="<c:out value='${book.preco}' />" /></td>
			</tr>
			<tr>
				<th>Genero:</th>
				<td><c:forEach var="gen" items="${book.genero}" varStatus="i">
						<input type="checkbox" name="genero" value="${gen.id}"
							checked="checked" id="${gen.id}" />
						<label for="${gen.id}">${gen.descricao}</label>
					</c:forEach> <c:forEach var="gen" items="${outrosGeneros}" varStatus="i">
						<input type="checkbox" name="genero" value="${gen.id}"
							id="${gen.id}" />
						<label for="${gen.id}">${gen.descricao}</label>
					</c:forEach></td>
			</tr>
			<tr>
				<td align="center"><input type="submit" value="Salvar" /></td>
				<td><a href="/Livraria/livro/list">Cancelar</a></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>
