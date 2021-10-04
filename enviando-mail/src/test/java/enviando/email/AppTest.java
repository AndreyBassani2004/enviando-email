package enviando.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class AppTest { 



	@org.junit.Test
	public void testeEmail(){
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");/*Autorizacao*/
		properties.put("mail.smtp.starttls", "true"); /*Autenticacao*/
		properties.put("mail.smtp.host", "stmp.gmail.com");/*Servidor google*/
		properties.put("mail.smtp.port", "465");/*Porta Servidor */
		properties.put("mail.smtp.socketFactory.port", "465");/*Expecifica a porta a ser conectada pelo socket*/
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe sockte de conexao ao SMTP*/
		
	}
		
}
