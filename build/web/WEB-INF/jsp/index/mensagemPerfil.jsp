<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
        <!-- Inclus�o do Jquery Validate -->
        <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.6/jquery.validate.js" ></script>
        <title>Pizza - On Demand! | Editar Mensagem do Perfil</title>
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
                        <div style="padding-bottom:10px; font-weight: bold; padding-left: 20px; border-bottom: 1px solid #773e3c; width: 90%;">Conte�do</div>
                        <div style="padding: 20px;">
<!--                            <form id="formularioPerfil" action="${pageContext.request.contextPath}/atualizaMensagemPerfil" method="POST" enctype="multipart/form-data">-->
                            <form id="formularioPerfil" method="POST" action="${pageContext.request.contextPath}/atualizaMensagemPerfil/${usuarioSession.user.id}" >
                                <fieldset style="width: 525px;">
                                    <legend style="padding: 0 10px;">Mensagem</legend>
                                    <table>
                                        <tr>
                                            <td><textarea name="pizzaria.mensagemPerfil" cols="60" rows="8" style="resize: none; text-align: justify; padding: 10px;"></textarea></td>
                                        </tr>
                                    </table>
                                    <input type="submit" id="enviar" value="Atualizar" style="width: 80px; font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px; margin: 10px;" />
                                    <input type="reset" id="enviar" value="Limpar" style="width: 80px; font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px; margin: 10px;" />
                                </fieldset>
                            </form>
                        </div>
                    </td>
                </tr>

                <tr>
                </tr>
            </table>
            <div style="display: none;" id="dialog" class="errorList"></div>
            <div style="display: none;" id="msg"></div>
            <div id="footer">
                Copyright 2012 � Pizza - On Demand! - Todos os direitos Reservados.
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
            
//            $('#formularioPerfil').submit(function(e){  
//                e.preventDefault();  
//                var serializeDados = $('#formularioPerfil').serialize();  
//                $.ajax({  
//                    url: '<c:url value="/atualizaMensagemPerfil" />',  
//                    type: 'POST',  
//                    data: serializeDados,  
//                    success: function(data, textStatus){  
////                        $("#msg").text("Mensagem gravada com sucesso!");
////                        $("#msg").dialog("open");  
//                    }  
//                });  
//                
//            });
            
            $(function() {
            $("#msg").dialog({
                modal: false,
                autoOpen: false,
                title: "Mensagem",
                width: 380,
                resizable: false,
                buttons: { 
                    Ok: function() {
                        $(this).text("");
                        $(this).dialog("close");
                    }
                },
                close: function() {
                    $(this).text("");
                }
            });
        });
        </script>

    </body>
</html>