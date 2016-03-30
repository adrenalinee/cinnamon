package org.cinnamon.core.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.cinnamon.core.domain.EmailServer;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.exception.BadRequestException;
import org.cinnamon.core.exception.InternalServerErrorException;
import org.cinnamon.core.exception.NotFoundException;
import org.cinnamon.core.repository.EmailServerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * create date: 2015. 4. 14.
 * @author 동성
 *
 */
@Service
public class EmailService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	EmailServerRepository emailServerRepository;
	
	// 자바 메일 sender
	private JavaMailSenderImpl mailSender;
	
	@Transactional
	public void send(String toAddress, String subject, String message) throws MessagingException {
		logger.info("start");
		
		EmailServer emailServer = emailServerRepository.getDefault();
		if (emailServer == null) {
			throw new BadRequestException("기본 이메일 서버가 지정되지 않았습니다.");
		}
		
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(emailServer.getAddress());
		mailSender.setUsername(emailServer.getUsername());
		mailSender.setPassword(emailServer.getPassword());
		mailSender.setPort(emailServer.getPort());
		
		
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setFrom(emailServer.getFromAddress());
		mimeMessageHelper.setTo(toAddress);
		mimeMessageHelper.setText(message);
		
		//mimeMessageHelper.addInline(contentId, inputStreamSource, contentType);
		
		mailSender.send(mimeMessage);
	}
	
	
	
	public void send() {
		System.out.println("send");
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setSubject("테스트 메일 입니다.");
		mailMessage.setFrom("dsshin@genexon.co.kr");
		mailMessage.setTo("dsshin@daihansci.co.kr");
		mailMessage.setText("테스트 메일 본문입니다.");
		
		
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("mail.genexon.co.kr");
		mailSender.setUsername("dsshin@genexon.co.kr");
		mailSender.setPassword("daihan@5962");
		
		mailSender.send(mailMessage);
	}
	
	
	public void send2() throws MessagingException {
		System.out.println("send");
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("mail.genexon.co.kr");
		mailSender.setUsername("dsshin@genexon.co.kr");
		mailSender.setPassword("daihan@5962");
		
		
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		
		mimeMessageHelper.setSubject("테스트 메일 2 입니다.");
		mimeMessageHelper.setFrom("dsshin@genexon.co.kr");
		mimeMessageHelper.setTo("dsshin@daihansci.co.kr");
		mimeMessageHelper.setText("테스트 메일 본문입니다.");
		
		//mimeMessageHelper.addInline(contentId, inputStreamSource, contentType);
		
		mailSender.send(mimeMessage);
	}
	
	public void t() {
		
//		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//		templateEngine.process("configuration/emailServers/template", context)
		
		
		
	}
	
	
//	public static void main(String[] args) throws Exception {
//		EmailService s = new EmailService();
//		s.send2();
//	}
	
	/**
	 * 인증메일 발송
	 * @author 정명성
	 * create date : 2016. 3. 30.
	 * @param userId
	 * @param userName
	 * @param email
	 * @param htmlContent
	 * @param domainName
	 */
	public void sendAuthorityMail(String userId, String userName, String email, String htmlContent, String domainName) {
		mailSender = new JavaMailSenderImpl();

		// 기본 smtp 서버 가져오기
		EmailServer emailServer = emailServerRepository.findByUseStatusAndDefaultServer(UseStatus.enable, true);

		if (emailServer == null) {
			throw new NotFoundException("기본 메일 서버를 찾을 수 없습니다 ");
		}

		// 호스트 셋팅
		this.mailSender.setHost(emailServer.getAddress());
		// 포트 셋팅
		this.mailSender.setPort(emailServer.getPort());

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				try {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
					message.setTo(email);
					message.setFrom("no-reply@daihan-biomedical.com"); // TODO 발신자정보 추후 변경
					message.setSubject("인증 확인 요청 메일 입니다.");
					message.setText(htmlContent, true);

				} catch (Exception e) {
					throw new InternalServerErrorException("인증메일 발송시 Error 발생! email address: " + email);
				}
			}
		};
		this.mailSender.send(preparator);
	}
}
