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
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World2!</h1>
        
<!--        <form id="cadastroPedido" action="${pageContext.request.contextPath}/cadastroPedido" method="post">
        
        <h2>Lista de produtos:</h2>
        <select name="pedido.produto.id">  
        <c:forEach items="${listaProdutos}" var="produto">  
            <option value="<c:out value="${produto.id}" />">${produto.descricao}</option>  
        </c:forEach>
        </select>

        <c:forEach items="${listaProdutos}" var="produto" varStatus="p">
            <input type="checkbox" name="${pedido.produto.id}" value="<c:out value="${produto.id}" />" />
             <c:out value="${produto.descricao}"></c:out>
        </c:forEach>
        
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
        </form>-->
        <form id="formulario" action="${pageContext.request.contextPath}/teste/cadastro/${pizzaria.id}" method="POST">
        <c:forEach items="${listaFormaPagamento}" var="lista" varStatus="s">
            <!--<input type="hidden" name="pizzariaFormaPagamento[${s.index}].pizzaria.id" value="${lista.id}" />-->
            <input type="checkbox" name="pizzariaFormaPagamento[${s.index}].formaPagamento.id" value="${lista.id}"> ${lista.descricao}<br>      
        </c:forEach> 

            <!--<input type="checkbox" value="1" name="pizzaria.pizzariaFormaPagamento" />Cartão Master-->
            <input type="submit" value="Enviar"/>
        </form>

</body>
<script>
//    $("#formulario").submit(function(){
//        alert("Dados: " + $("#formulario").serialize());
//    });
</script>
</html>
