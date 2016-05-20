package org.cinnamon.web.configuration.restController

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

import nl.captcha.Captcha

import org.cinnamon.core.exception.BadRequestException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value="/rest/captcha")
class CaptchaRestController {

	/**
	 * captcha 검증
	 * @author 정명성
	 * @create date : 2016. 5. 20.
	 * @param request
	 * @param answer
	 * @return
	 */
	@RequestMapping(value="check")
	def checkCaptcha(HttpServletRequest request, String answer) {
		HttpSession session = request.getSession(false);

		Captcha captcha = (Captcha) session.getAttribute("simpleCaptcha");
		
		if(answer == null) {
			throw new BadRequestException("answer is null");
		}
		
		if (captcha.isCorrect(answer)) {
			ResponseEntity.ok().build()
		} else {
			ResponseEntity.notFound().build()
		}
	}
}
