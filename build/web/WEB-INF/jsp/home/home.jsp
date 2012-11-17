<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <!-- CSS -->
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all">
        <link href="css/jquery-ui-1.9.1.custom.min.css" rel="stylesheet" type="text/css" media="all">
        <!-- Scripts -->
        <script src="scripts/jquery-1.8.2.js"></script>
        <script src="scripts/jquery-ui-1.9.1.custom.js"></script>
        <script src="scripts/jquery-ui-1.9.1.custom.min.js"></script>
        
        <style>
            .ui-dialog {
                border: 6px solid #d49768;
            }
        </style>
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

                            <div style="float: right; padding-right: 20px; margin-top: 0px; font-size: 12px;">
                                Ainda não tem cadastro? <a href="${pageContext.request.contextPath}/cadastroPizzaria"><b>Cadastre-se</b></a><br>
                                Esqueceu a senha? <a href="#" id="verificaSenha"><b>Clique aqui.</b></a>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
            <div style="display: none;" id="msg"></div>
            <div style="display: none; padding: 20px;" id="recuperaSenhaDialog">
                <form id="formRecuperaSenha">
                    Digite o email: <input style="margin-left: 10px;" type="text" id="email" name="pizzaria.email" />
                </form>
            </div>
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

            $("#msg").dialog({
                title: "Mensagem",
                modal: false,
                autoOpen: false,
                width: 380,
                resizable: false,
                buttons: {
                    Fechar: function() {
                        $(this).text("");
                        $("#email").val("");
                        $(this).dialog("close");
//                        $("#recuperaSenhaDialog").dialog("close");
                    },
                },
                close: function() {
                    $(this).text("");
//                    $("#recuperaSenhaDialog").dialog("close");
                }
            });

            var email = $("#email").val();

            $("#recuperaSenhaDialog").dialog({
                title: "Recuperar Senha",
                modal: false,
                autoOpen: false,
                width: 380,
                resizable: false,
                buttons: {
                    Fechar: function() {
                        $("#email").val("");
                        $(this).dialog("close");
                    },
                    Enviar: function() {
                        var email = $("#email").val();
        //                alert(email);
                        if(email != "") {
                            $.getJSON('<c:url value="/verificaEmail"/>' + '?pizzaria.email=' + email,
                                    function(data) {
                                        console.log(data);
                                        if (data == true) {
                                            $.ajax({
                                                url: '<c:url value="/recuperarSenha" />',
                                                type: "POST",
                                                data: $("#formRecuperaSenha").serialize(),
                                                success: function(data) {
                                                    if ($(data).find('pizzaria.email')) {
            //                                                alert("Você recebera um email com a confirmação da sua senha.");                            
                                                        $("#msg").text("Enviamos um email para " + data.pizzaria.email + " informando sua nova senha.");
                                                        $("#msg").dialog("open");
            //                                                alert("==> " + data.pizzaria.email);                            
                                                    } else {
                                                        $("#msg").text("Email informado não existe.");
                                                        $("#msg").dialog("open");
//                                                        alert("Email informado não existe.");
                                                    }
                                                },
                                                error: function(data) {
                                                }
                                            });
                                        } else {
//                                            alert("Email informado não existe.");
                                            $("#msg").text("Email informado não existe.");
                                            $("#msg").dialog("open");
                                        }
                                    });
                        } else {
//                            alert("O campo email não pode ser vazio!");
                            $("#msg").text("O campo email não pode ser vazio.");
                            $("#msg").dialog("open");
                        }
                    }
                },
                close: function() {
                    $("#email").val("");
                }
            });

            $("#verificaSenha").click(function() {
                $("#recuperaSenhaDialog").dialog("open");
            });
        </script>

    </body>
</html>