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
		<c:if test="${pessoa != null}">
			<form action="update" method="post">
		</c:if>
		<c:if test="${pessoa == null}">
			<form action="insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${pessoa != null}">
            			Editar Cliente
            		</c:if>
					<c:if test="${pessoa == null}">
            			Criar Cliente
            		</c:if>
				</h2>
			</caption>
			<c:if test="${pessoa != null}">
				<input type="hidden" name="id" value="<c:out value='${pessoa.id}' />" />
			</c:if>
				
			<tr>
				<th>Nome:</th>
				<td><input type="text" name="nome" size="45"
					value="<c:out value='${pessoa.nome}' />" /></td>
			</tr>
			<tr>
				<th>Endereço:</th>
				<td><input type="text" name="endereco" size="45"
					value="<c:out value='${pessoa.endereco}' />" /></td>
			</tr>
			<tr>
				<th>Número do cartão:</th>
				<td><input type="text" name="num_cartao" size="45"
					value="<c:out value='${pessoa.num_cartao}' />" /></td>
			</tr>
			<tr>
				<th>CVV:</th>
				<td><input type="text" name="cvv"
					value="<c:out value='${pessoa.cvv}' />" /></td>
			</tr>
			
			<tr>
				<th>Validade do cartão:</th>
				<td><input type="text" name="validade_cartao"  pattern="[0-9]{2}[0-9]{2}" title="MMAA"
					value="<c:out value='${pessoa.validade_cartao}' />" /></td>
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
