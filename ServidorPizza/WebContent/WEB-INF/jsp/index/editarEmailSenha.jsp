<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza - On Demand! | Editar Cadastro</title>
    </head>
    <body>
        <div id="container">
            <table border="0" width="850px" align="center" style="text-align: center; color: #5a0805; font-size: 14px;">
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <img src="imagens/logo_inicial.png" width=20% height=35% />
                    </td>
                </tr>
                <tr><td colspan="2" style="height: 30px;"></td></tr>
                <tr>
                    <td height="150px" style="text-align: left; padding-left: 18px;">
                <c:if test="${pizzaria.imagemPerfil != null}">
                    <img class="imagem" src="<c:url value="/mostraImagem/${pizzaria.id}/imagem"/>" width="150" height="150" />
                </c:if>
                <c:if test="${pizzaria.imagemPerfil == null}">
                    <div class="imagem" style="width: 150px; height: 150px;">
                        <div style="padding-top: 67px; padding-left: 28px; color: #d1cfbd;">
                            Sem Imagem
                        </div>
                    </div>
                </c:if>                    
                </td>
                <td style="vertical-align: top;">
                    <div style="text-align: justify; background-image: url('imagens/fundomensagem.png'); background-repeat: no-repeat; width: 574px; height: 164px;">
                        <c:if test="${pizzaria.mensagemPerfil != null}">
                        <div style="width: 500px; margin-left: 45px; padding-top: 25px;">
                            ${pizzaria.mensagemPerfil}
                        </div>
                        </c:if>
                        <c:if test="${pizzaria.mensagemPerfil == null}">
                        <div style="width: 500px; margin-left: 45px; padding-top: 67px; color: #d1cfbd; text-align: center;">
                            Sem Mensagem de Perfil
                        </div>
                        </c:if>
                        <c:if test="${pizzaria.mensagemPerfil == ''}">
                        <div style="width: 500px; margin-left: 45px; padding-top: 42px; color: #d1cfbd; text-align: center;">
                            Sem Mensagem de Perfil
                        </div>
                        </c:if>
                    </div>
                </td>			
                </tr>
                <tr>
                    <td width="220px" style="padding-top: 30px; text-align: left; vertical-align: top;">
    
                        <jsp:include page="../template/menu.jsp" />

                    </td>
                    <td style="padding-top: 30px; text-align: left;  vertical-align: top;">
                        <jsp:include page="../template/conteudo.jsp" />
                        <div style="padding: 20px;">
                            <!--<form id="formAtualizaCadastro" action="${pageContext.request.contextPath}/atualizarPizzaria/${pizzaria.id}" method="POST">-->
                            <form id="formAtualizaCadastro" method="POST">
                                <table border="0" width="94%" align="left" style="color: #5a0805; font-size: 18px;">
                                    <tr>
                                        <td colspan="2" style="padding-top: 20px; border-bottom: 1px solid #773e3c; font-size: 14px; font-weight: bold; padding-bottom: 10px;">Dados de acesso ao Sistema</td>
                                    </tr>
                                    <tr><td colspan="2" style="height: 20px;"></td></tr>	
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Email da Pizzaria: </td>
                                        <td>
                                            <input type="text" name="pizzaria.email" value="${pizzaria.email}" id="email" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Senha: </td>
                                        <td>
                                            <input type="password" name="pizzaria.senha" id="senha" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                            
                                        </td>
                                    </tr>
                                    <tr><td colspan="2" style="height: 20px;"></td></tr>
                                    <tr>
                                        <td colspan="2" style="height: 50px; text-align: center;">
                                            <input type="submit" id="enviar" value="Atualizar" style="width: 80px; font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px" />
                                            <input type="reset" id="limpar" value="Limpar" style="margin-left: 80px;  width: 80px; font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px" />
                                        </td>	
                                    </tr>
                                </table>
                            </form>                      
                        </div>
                    </td>
                </tr>
                <tr>
                </tr>
            </table>
            <div style="display: none;" id="msg"></div>
            <div style="display: none;" id="dialog" class="errorList">
                Confirma a alteração do seu Email e da Sua senha?
            </div>
            <div id="footer">
                Copyright 2012  Pizza - On Demand! - Todos os direitos Reservados.
            </div>
        </div>

        <script type="text/javascript">
            //Execute the function when window load
            $(document).ready(function() {
   
                $('#pizzaria_estado').val( '${pizzaria.estado}' );
   
                var docHeight = $(window).height();
                var footerHeight = $('#footer').height();
                var footerTop = $('#footer').position().top + footerHeight;
   
                if (footerTop < docHeight) {
                    $('#footer').css('margin-top', 10 + (docHeight - footerTop) + 'px');
                }
            
                $("#msg").dialog({
                    title: "Mensagem",
                    modal: false,
                    autoOpen: false,
                    width: 380,
                    resizable: false,
                    buttons: {
                        Fechar: function() {
                            $(this).text("");
                            $(this).dialog("close");
                        },
                    },
                    close: function() {
                        $(this).text("");
                    }
                });

                $("#dialog").dialog({
                        modal: false,
                        autoOpen: false,
                        title: "Atenção",
                        width: 380,
                        resizable: false,
                        buttons: { 
                            Fechar: function() {
                                $(this).text("");
                                $(this).dialog("close");
                                $("#pizzaria.senha").text("");
                            },
                            Confirmar: function() {
                                $.ajax({
                                    url: '<c:url value="/atualizarPizzaria/${pizzaria.id}" />',
                                    type: "POST",
                                    data: $("#formAtualizaCadastro").serialize(),
                                    success: function(data) {
                                        window.location = '/ServidorPizza/index';
                                    }
                                });
                            }
                        },
                        close: function() {
                            $(this).text("");
                            $("#pizzaria.senha").text("");
                        }
                    });


                $("#formAtualizaCadastro").submit(function(e){
                    e.preventDefault();
                    var email = $("#email").val();
                    alert(email);
                    if($("#senha").val().length < 6){
                        $("#msg").append("O campo <b>Senha</b> precisa ter pelo menos 6 caracteres.<br>");
                        $("#msg").dialog("open");
                    } 
                    if($("#email").val() === "") {
                        $("#msg").append("O campo <b>Email</b> não pode ser nulo.<br>");
                        $("#msg").dialog("open");
                    }
                    if($("#senha").val().length >= 6 && $("#email").val() !== "") {
                        $("#dialog").dialog("open");
                    }
                });
            });
        </script>

    </body>
</html>