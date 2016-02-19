package com.ftd.simplespider.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @ClassName: TestController 
 * @author bjlixing dd
 */
@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("t!test.action")
	public String test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("in");
		try {
			return "/template/test";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
