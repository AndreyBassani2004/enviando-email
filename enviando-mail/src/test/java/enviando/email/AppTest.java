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
		
		
		StringBuilder stringBuilderTextEmail = new StringBuilder();
		
		stringBuilderTextEmail.append("Ola, <br><br>");
		stringBuilderTextEmail.append("<h3>Voce ganhou vale R$ 0</h3> <br><br>");
		stringBuilderTextEmail.append("<a href='https://www.uol.com.br'> <button type='button' id='button1' name='button' style='background-color: #4CAF50; border: none;color: white;padding: 15px 32px; text-align: center;text-decoration: none; display: inline-block; font-size: 16px;'>Click!</button> </a> ");
		
		
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("andreyjose2000@gmail.com, andreyjoseab@outlook.com", "Bacuri Suporte - 2000", "Email enviado com java SSL", stringBuilderTextEmail.toString());
		
         enviaEmail.enviarAnexo(true);
		
		/*Caso o email não esteja sendo enviado então 
		 * coloque um tempo de espera mais isso só pode 
		 * ser usado para testes.*/
		Thread.sleep(5000);
		
	}
		
	
}
