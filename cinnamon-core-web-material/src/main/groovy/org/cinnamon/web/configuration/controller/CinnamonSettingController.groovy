package org.cinnamon.web.configuration.controller

import org.springframework.web.bind.annotation.RequestMapping

/**
 * 
 * @author 신동성
 * @since 2016. 6. 20.
 */
class CinnamonSettingController {

	@RequestMapping("**")
	def main() {
		"configuration/cinnamon"
	}
}
