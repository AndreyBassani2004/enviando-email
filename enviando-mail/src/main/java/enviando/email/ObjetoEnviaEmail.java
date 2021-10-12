package enviando.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
    
    
    
    public void enviarEmail(boolean envioHtml) throws Exception{
    	
    	
    		
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
    	   
    	   if(envioHtml) {
    		   message.setContent(textEmail, "text/html; charset=utf-8");
    	   }else {  	   
    	   message.setText(textEmail);
    	   }
    	   Transport.send(message);
    	   
    		
    	}
   
    
    
    public void enviarAnexo(boolean envioHtml) throws Exception{
    	
    	
		
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
		message.setFrom(new InternetAddress(userName, nomeRemetente)); /*Quem está enviano*/
		message.setRecipients(Message.RecipientType.TO, toUser);/*Email de destino*/
		message.setSubject(assuntoEmail);/*Assunto do e-mail*/
	   
	   
	   //Parte 1 do e-mail texto e descricao do e-mail
	   MimeBodyPart corpoEmail = new MimeBodyPart();
	   
	   
	   if(envioHtml) {
		   corpoEmail.setContent(textEmail, "text/html; charset=utf-8");
	   }else {  	   
	      corpoEmail.setText(textEmail);
	   }
	   
	   
	   List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
	   arquivos.add(simuladorDePDF());
	   arquivos.add(simuladorDePDF());
	   arquivos.add(simuladorDePDF());
	   arquivos.add(simuladorDePDF());
	   
	   
	   Multipart multipart = new MimeMultipart();
	   multipart.addBodyPart(corpoEmail);
	   
	   
	   int index = 0;
	   for (FileInputStream fileInputStream : arquivos) {
		      
	   //Parte 2 envio PDF
	   MimeBodyPart anexoEmail = new MimeBodyPart();
	   
	   //Onde é Passado o simuladorPDF voce passa o arquivo gravado no BD ou em outro local
	   anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
	   anexoEmail.setFileName("anexo"+index+".pdf");
	   
	   
	   multipart.addBodyPart(anexoEmail);
	   
	   index++;
	   }
	   message.setContent(multipart);
		
	  Transport.send(message);
	   
		
	}
    
    
    //Esse metodo simula o PDF ou qualquer arquivo que possa ser enviado por anexo no email
    // Voce pode pegar o arquivo no seu banco de dados base64, byte[], Stream de arquivo
    //Pode estar no bd, ou em pasta
    //Retorno em um pdf em Branco com o texto paragrafo
    
    
    private FileInputStream simuladorDePDF() throws Exception{
    	Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo com Java Mail, esse texto é do PDF"));
		document.close();
		
		return new FileInputStream(file);
    }
    
}


