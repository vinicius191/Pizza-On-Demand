<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Pizza - On Demand! | Editar Imagem do Perfil</title>
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
                            <form id="formAtualizaImagem" action="${pageContext.request.contextPath}/atualizaImagemPerfil/${usuarioSession.user.id}/imagem" method="POST" enctype="multipart/form-data">
                                <fieldset style="width: 525px;">
                                    <legend style="padding: 0 10px;">Upload de Imagem</legend>
                                    <table>
                                        <tr>
                                            <td><input type="file" name="imagem" id="imagem" /></td>
                                        </tr>
                                    </table>
                                <input type="submit" id="enviar" value="Enviar" style="width: 80px; font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px; margin: 10px;" />
                                </fieldset>
                            </form>
                            <br>
                            <form id="formRemoveImagem" action="${pageContext.request.contextPath}/removeImagemPerfil/${usuarioSession.user.id}" method="POST">
                                <fieldset style="width: 525px;">
                                    <legend style="padding: 0 10px;">Excluir Imagem</legend>
                                    <input type="submit" id="enviar" value="Excluir" style="width: 80px; font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px; margin: 10px;" />
                                </fieldset>
                            </form>                          
                        </div>
                    </td>
                </tr>
                <tr>
                </tr>
            </table>
            <div style="display: none;" id="dialog-validation" class="errorList">
            </div>
            <div style="display: none;" id="dialog" class="errorList"></div>
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
            
            $("#imagem").change(function() {

               var val = $(this).val();
               var valor = val.substring(val.lastIndexOf('.') + 1).toLowerCase();
               var size = $("#imagem")[0].files[0].size;
               
               if(valor !== 'gif' && valor !== 'jpg' && valor !== 'png') {
                  $("#dialog-validation").append("A imagem selecionada precisa ser dos tipos: <b>JPG, GIF ou PNG.</b><br>");
                  $("#dialog-validation").dialog("open"); 
                  $(this).val("");
               }
               
               if(size > 1500000) {
                  $("#dialog-validation").append("<br>O tamanho máximo da imagem é de <b>1.5M.</b>");
                  $("#dialog-validation").dialog("open");
                  $(this).val("");
               }

           });
       
            $(function() {
            $("#dialog-validation").dialog({
                position: ['middle',20],
                modal: false,
                autoOpen: false,
                title: "Verifique os campos abaixo",
                width: 380,
                resizable: false,
                buttons: { 
                    Fechar: function() {
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