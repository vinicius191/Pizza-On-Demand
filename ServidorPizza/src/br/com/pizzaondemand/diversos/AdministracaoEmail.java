/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaondemand.diversos;

import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Almir Campos
 */

//Classe necessaria para passar usuario e senha caso precisa de autenticacao
class SMTPAuthenticator extends Authenticator {
    String login;
    String password;

    //Recebendo usuario e senha e guardando nas variaveis
    public SMTPAuthenticator(String login, String password) {
            this.login = login;
            this.password = password;
    }

    //Esse é o metodo usado para enviar usuario e senha
    @Override
    public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.login, this.password);
    }
}

public class AdministracaoEmail
{

    public static void EnviarEmail (String assunto, String conteudo, String destinatario) 
    { 
        try {
            //Variaveis
            String  d_email = "naoresponda.pizzaondemand@gmail.com",
                    d_password = "pizzaondemand321",
                    d_host = "smtp.gmail.com",
                    d_port = "465",
//            String  d_email = "naoresponda.simvendasweb@gmail.com",
//                    d_password = "naorespondauniversia",
//                    d_host = "smtp.gmail.com",
//                    d_port = "465",
                    
                    //Destinatario, Titulo e Conteudo da mensagem
                    m_to = destinatario,
                    m_subject = assunto,
                    m_text = conteudo;
 
            //Propriedades Necessarias
            Properties props = new Properties();
             
            //Modo debug para verificar os passos do envio
            props.put("mail.debug", "true");
             
            //Servidor SMTP
            props.put("mail.host", d_host);
             
            //Porta
            props.put("mail.smtp.port", d_port);
             
            //Necessario autenticacao
            props.put("mail.smtp.auth", "true");
             
            //Liga o SSL
            props.put("mail.smtp.ssl.enable", "true");
 
            //Cria a sessao
            Session session = Session.getInstance(props,  new SMTPAuthenticator(d_email, d_password));
 
            //Pega a sessao com usuario e senha
            MimeMessage msg = new MimeMessage(session);
             
            //Coloca O corpo do titulo
            msg.setText(m_text, "UTF-8", "html");
             
            //Coloca o assunto
            msg.setSubject(m_subject);
             
            //Coloca quem enviou
            msg.setFrom(new InternetAddress(d_email));
             
            //Coloca para quem sera enviado
            msg.addRecipient(Message.RecipientType.TO,  new InternetAddress(m_to));
             
            //Envia a mensagem
            Transport.send(msg);
 
            System.out.println("Terminado");
            
        } catch (MessagingException mex) {
            System.out.println("Falha no envio, exception: " + mex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  

}
