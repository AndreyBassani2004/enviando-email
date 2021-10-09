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

public class ObjetoEnviaEmail {

	private String userName = "bacurisuporte@gmail.com";
    private String senha = "positivo2011";
    
    private String listaDestinatarios = "";
    private String nomeRemetente = "";
    private String assuntoEmail = "";
    private String textEmail = "";
    
       
    public ObjetoEnviaEmail(String listaDestinatario, String nomeRemetente, String assuntoEmail, String textEmail) {
    	this.listaDestinatarios = listaDestinatario;
    	this.nomeRemetente = nomeRemetente;
    	this.assuntoEmail = assuntoEmail;
    	this.textEmail = textEmail;
    	
    	
    }
    
    
    
    public void enviarEmail() throws Exception{
    	
    	
    		
    		Properties properties = new Properties();
    		
    		
    		properties.put("mail.smtp.ssl.trust", "*");
    		properties.put("mail.smtp.auth", "true");/*Autorizacao*/
    		properties.put("mail.smtp.starttls", "true"); /*Autenticacao*/
    		properties.put("mail.smtp.host", "smtp.gmail.com");/*Servidor google*/
    		properties.put("mail.smtp.port", "465");/*Porta Servidor */
    		properties.put("mail.smtp.socketFactory.port", "465");/*Expecifica a porta a ser conectada pelo socket*/
    		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe sockte de conexao ao SMTP*/
    		
    		Session session = Session.getInstance(properties, new Authenticator() {
    			
    			@Override
    			protected PasswordAuthentication getPasswordAuthentication() {
    				
    				return new PasswordAuthentication(userName, senha);
    			}
    			
    		});
    		
    	   Address[] toUser = InternetAddress.parse(listaDestinatarios);
    	   
    	   Message message = new MimeMessage(session);
    	   message.setFrom(new InternetAddress(userName, nomeRemetente)); //Quem esta enviando
    	   message.setRecipients(Message.RecipientType.TO, toUser);	//Email destino
    	   message.setSubject(assuntoEmail); //Asunto email
    	   message.setText(textEmail);
    	   
    	   Transport.send(message);
    	   
    		
    	}
   
    
}


