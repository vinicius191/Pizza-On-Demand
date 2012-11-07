<%-- 
    Document   : teste
    Created on : 01/11/2012, 15:48:20
    Author     : cvinicius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World2!</h1>
        
        <form id="cadastroPedido" action="${pageContext.request.contextPath}/cadastroPedido" method="post">
        
        <h2>Lista de produtos:</h2>
        <select name="pedido.produto.id">  
        <c:forEach items="${listaProdutos}" var="produto">  
            <option value="<c:out value="${produto.id}" />">${produto.descricao}</option>  
        </c:forEach>
        </select>
        
        <h2>Dados do pedido:</h2>
        Endereço do pedido: <input type="text" name="pedido.enderecoEntrega" /><br>
        Status: <input type="text" name="pedido.status" /><br>
        Quantidade: <input type="text" name="pedido.quantidade" /><br>
        
        <h2>Usuario android</h2>
        <select name="pedido.usuarioAndroid.id">  
        <c:forEach items="${listaUsuarios}" var="usuarioAndroid">  
            <option value="<c:out value="${usuarioAndroid.id}" />">${usuarioAndroid.nome}</option>  
        </c:forEach>
        </select>
        
        <br><br>
        
        <input type="submit" values="Enviar">
        </form>
        
   
</body>
</html>
