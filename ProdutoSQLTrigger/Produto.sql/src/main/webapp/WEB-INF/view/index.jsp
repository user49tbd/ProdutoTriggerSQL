<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" 
href='<c:url value="./resources/CSS/cf.css"/>'>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<div align="center">
<p>Menu</p>
</div>

<br>

<div align="center">
<form action="index" method="post">
<table>
<tr>
<td colspan="3"><input type="number" name="btcodigo" id="btcodigo" placeholder="Codigo"
step="1" min="1" value="<c:out value="${prod.codigo}"></c:out>"></td>
<td colspan="1"><input type="submit" name="bt" id="bt" value="Buscar"></td>
</tr>
<tr>
<td colspan="4"><input type="text" name="btnome" id="btnome" placeholder="Nome"
value="<c:out value="${prod.nome}"></c:out>"></td>
</tr>
<tr>
<td colspan="4"><input type="number" name="btvu" id="btvu" placeholder="Valor Unitario"
step="0.01" value="<c:out value="${prod.vu}"></c:out>"></td>
</tr>
<tr>
<td colspan="2"><input type="number" name="btqtd" id="btqtd" placeholder="Quantidade"
step="1" value="<c:out value="${prod.qtd}"></c:out>"></td>
</tr>
<tr>
<td colspan="1"><input type="submit" name="bt" id="bt" value="Inserir"></td>
<td colspan="1"><input type="submit" name="bt" id="bt" value="Atualizar"></td>
<td colspan="1"><input type="submit" name="bt" id="bt" value="Deletar"></td>
<td colspan="1"><input type="submit" name="bt" id="bt" value="Listar"></td>
</tr>
</table>
</form>
</div>
<br>
<div align="center">
<c:if test="${not empty err}">
<c:out value="${err}"></c:out>
</c:if>
</div>
<br>
<div align="center">
<c:if test="${not empty lisprod }">
<table>
<thead>
<tr>
<th>Codigo</th>
<th>Nome</th>
<th>Valor_Unitario</th>
<th>Quantidade</th>
</tr>
</thead>
<tbody>
<c:forEach items="${lisprod}" var="p">
<tr>
<td><c:out value="${p.codigo}"></c:out></td>
<td><c:out value="${p.nome}"></c:out></td>
<td><c:out value="${p.vu}"></c:out></td>
<td><c:out value="${p.qtd}"></c:out></td>
</tr>
</c:forEach>
</tbody>
</table>
</c:if>
</div>
</body>
</html>