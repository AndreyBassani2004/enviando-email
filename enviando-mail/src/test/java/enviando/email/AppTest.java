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
	public void testeEmail() throws Exception{
		
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("andreyjose2000@gmail.com, andreyjoseab@outlook.com", "Bacuri Suporte - 2000", "Email enviado com java SSL", "Ola Mundo!");
		
	    enviaEmail.enviarEmail();
		
	}
		
}
