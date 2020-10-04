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
			<a href="/Livraria/compra/new">Nova Compra</a> &nbsp;&nbsp;&nbsp; 
			<a href="/Livraria/compra/list">Listar Compras</a>

		</h2>
	</center>

	<div align="center">
		<c:if test="${compra != null}">
			<form action="update" method="post">
		</c:if>
		<c:if test="${compra == null}">
			<form action="insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${compra != null}">
            			Editar Compra
            		</c:if>
					<c:if test="${compra == null}">
            			Registrar uma Compra
            		</c:if>
				</h2>
			</caption>
			<c:if test="${compra != null}">
				<input type="hidden" name="id" value="<c:out value='${compra.id_compra}' />" />
			</c:if>
				
			<tr>
				<th>Cliente:</th>
				<td><input type="text" name="pessoa_id" size="45"
					value="<c:out value='${compra.id_pessoa}' />" /></td>
			</tr>
			<tr>
				<th>Livro:</th>
				<td><input type="text" name="livro_id" size="45"
					value="<c:out value='${compra.id_livro}' />" /></td>
			</tr>
			<tr>
				<th>Quantidade:</th>
				<td><input type="text" name="quantidade" size="45"
					value="<c:out value='${compra.quantidade}' />" /></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Salvar" />
					<a href="/Livraria/compra/list">Cancelar</a>
					</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>
