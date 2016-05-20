package org.cinnamon.web.configuration.controller

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

import nl.captcha.Captcha
import nl.captcha.audio.AudioCaptcha
import nl.captcha.audio.producer.CustomNumberVoiceProducer
import nl.captcha.backgrounds.GradiatedBackgroundProducer
import nl.captcha.servlet.CaptchaServletUtil
import nl.captcha.text.producer.CustomTextProducer
import nl.captcha.text.producer.NumbersAnswerProducer

import org.cinnamon.core.exception.BadRequestException
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping(value="/captcha")
class CaptchaController {

	protected int _width = 200

	protected int _height = 50

	/**
	 * 캡차 생성
	 * @author 정명성
	 * @create date : 2016. 5. 20.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping
	def createCaptcha(HttpServletRequest request, HttpServletResponse response) {
		Captcha captcha = new Captcha.Builder(_width, _height)
				.addText(new NumbersAnswerProducer())
				.addBackground(new GradiatedBackgroundProducer())
				.gimp()
				.addNoise()
				.addBorder()
				.build()

		request.getSession().setAttribute("simpleCaptcha", captcha)
		CaptchaServletUtil.writeImage(response, captcha.getImage())
	}

	/**
	 * 캡차 오디오 셋팅
	 * @author 정명성
	 * @create date : 2016. 5. 20.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="audio")
	def createCaptchaAudio (HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false)

		Captcha captcha = (Captcha) session.getAttribute("simpleCaptcha")
		CustomTextProducer ctp = new CustomTextProducer(captcha.getAnswer().toCharArray())
		CustomNumberVoiceProducer cvp = new CustomNumberVoiceProducer(captcha.getAnswer().toCharArray(), "kr")

		AudioCaptcha ac = new AudioCaptcha.Builder()
				.addAnswer(ctp)
				.addVoice(cvp)
				.addNoise()
				.build("kr")

		request.getSession().setAttribute("simpleCaptchaAfterAudio", ac)
		CaptchaServletUtil.writeAudio(response, ac.getChallenge())
	}

}
