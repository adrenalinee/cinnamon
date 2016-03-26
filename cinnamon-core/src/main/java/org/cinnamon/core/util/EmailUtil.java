package org.cinnamon.core.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import org.cinnamon.core.domain.EmailServer;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	BufferedReader in = null;
	PrintWriter out = null;

	Socket smtpPipe;
	InputStream inn;
	OutputStream outt;
	StringBuffer sb = null;
	
	/**
	 * 메일 서버 발송 테스트
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param emailServer
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused"})
	public String sendMailTest(EmailServer emailServer, String toAddress) throws Exception {

		try {

			InetAddress mailHost = InetAddress.getByName(emailServer.getAddress());
			InetAddress localhost = InetAddress.getLocalHost();
			smtpPipe = new Socket(emailServer.getAddress(), emailServer.getPort());

			if (smtpPipe == null) {
				// throw new Exception("smtp pipe create fail!!");
				//System.out.println("smtp pipe create fail!!.");
				return "smtp pipe create fail!!.";
			}

			inn = smtpPipe.getInputStream();
			outt = smtpPipe.getOutputStream();
			in = new BufferedReader(new InputStreamReader(inn));
			out = new PrintWriter(new OutputStreamWriter(outt), true);
			
			if (inn == null || outt == null) {
				//System.out.println("Failed to open streams to socket.");
				return "Failed to open streams to socket.";
			}

			sb = new StringBuffer();
			
			sb.append("\n" + in.readLine()).append("HELO " + localhost.getHostName());
			out.println("HELO " + localhost.getHostName());
			sb.append(in.readLine() + "\n").append("MAIL From: " + emailServer.getFromAddress() + " \n");

			out.println("mail from: <" + emailServer.getFromAddress() + ">");
			sb.append(in.readLine() + "\n").append("RCPT TO: <audtjddld@daihan-biomedical.com>\n");

			out.println("rcpt to : <" + toAddress + ">");
			sb.append(in.readLine() + "\n");
			sb.append("SUBJECT:Test Email. \n").append("DATA");

			out.println("DATA");
			out.println("subject : test");
			out.println("\n Test Email ");
			out.println(".");
			sb.append(in.readLine() + "\n");
			
			sb.append("delete please.");
			out.println("QUIT");
			sb.append(in.readLine() + "\n").append("QUIT \n");
			
			/* TODO 수정 예정
			setCommandAndSaveServerMsg ("HELO " + localhost.getHostName(), sb);
			setCommandAndSaveServerMsg ("MAIL From: <" + emailServer.getFromAddress() + ">", sb);
			setCommandAndSaveServerMsg ("rcpt to : <audtjddld@daihan-biomedical.com>", sb);
			setCommandAndSaveServerMsg ("data", sb);
			setCommandAndSaveServerMsg ("subject : test", sb);
			setCommandAndSaveServerMsg ("\n  test mail", sb);
			setCommandAndSaveServerMsg (".", sb);
			setCommandAndSaveServerMsg ("QUIT", sb);
			*/
			
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			return "서버 정보가 정확하지 않습니다. 다시 확인해주세요.";
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}
		return sb.toString();
	}
	
	/**
	 * 명령어 실행 및 서버 로그 저장
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param command
	 * @param sb
	 * @throws Exception
	 */
	public void setCommandAndSaveServerMsg (String command, StringBuffer sb) throws Exception{
		if( this.out == null ) {
			System.out.println("PrintWriter is null.");
		}
		if( this.in == null ) { 
			System.out.println("BufferReader is null.");
		}
		this.out.println(command);
		sb.append(this.in.readLine() + "\n");
		System.out.println(sb.toString());
	}
}
