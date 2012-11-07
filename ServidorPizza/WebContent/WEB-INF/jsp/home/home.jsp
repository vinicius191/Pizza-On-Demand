<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pizza - On Demand! | Login</title>
</head> 
<body>

<div id="container">
<div style="background: url('imagens/logo_inicial.png') no-repeat; width: 478px; height: 426px; margin: 0 auto;"></div>
<form id="formularioLogin" action="${pageContext.request.contextPath}/autenticar" method="post">
<table width="450" height="auto" border="0" align="center" style="margin-top: 30px; color: #5a0805; font-size: 18px;">
	<tr>
		<td>Login:</td>
		<td><input type="text" name="pizzaria.email" style="width: 100%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" /></td>
	</tr>
	<tr>
		<td>Senha:</td>
		<td><input type="password" name="pizzaria.senha" style="width: 100%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" /></td>
	</tr>
	<tr>
		<td colspan="2" style="padding-top: 40px; padding-left: 30px;">
		<input type="submit" id="enviar" value="Entrar" style="width: 80px; font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px" />
		
		<span style="margin-left: 40px;font-size: 12px;">Ainda não tem cadastro? <a href="${pageContext.request.contextPath}/cadastroPizzaria">Cadastre-se</a></span>
		</td>
	</tr>
</table>
</form>
<div style="height: 20px; margin-top: 30px; margin-bottom: -20px;">
<c:if test="${error eq 'E-mail ou senha incorreta!'}">
	<div style="width:250px; text-align:center;  margin: 0 auto; font-size: 14px; color:#5a0805;">Usuário ou senha incorretos!</div>
</c:if>
</div>
<div id="footer">
Copyright 2012 © Pizza - On Demand! - Todos os direitos Reservados.
</div>
</div> 

<script type="text/javascript">
//Execute the function when window load
$(document).ready(function() {
   
   var docHeight = $(window).height();
   var footerHeight = $('#footer').height();
   var footerTop = $('#footer').position().top + footerHeight;
   
   if (footerTop < docHeight) {
    $('#footer').css('margin-top', 10 + (docHeight - footerTop) + 'px');
   }
  });
</script>

</body>
</html>