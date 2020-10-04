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
		<c:if test="${genero != null}">
			<form action="update" method="post">
		</c:if>
		<c:if test="${genero == null}">
			<form action="insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${genero != null}">
            			Editar Gênero
            		</c:if>
					<c:if test="${genero == null}">
            			Criar Gênero
            		</c:if>
				</h2>
			</caption>
			<c:if test="${genero != null}">
				<input type="hidden" name="id" value="<c:out value='${genero.id}' />" />
			</c:if>
			<tr>
				<th>Descricao:</th>
				<td><input type="text" name="descricao" size="45"
					value="<c:out value='${genero.descricao}' />" /></td>
			</tr>
			
			<tr>
				<td  colspan="2" align="center"><input type="submit"
					value="Salvar" />
					<a href="/Livraria/pessoa/list">Cancelar</a>
					</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>
