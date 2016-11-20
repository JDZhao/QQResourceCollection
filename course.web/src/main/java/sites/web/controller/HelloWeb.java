package sites.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhaojd
 * @date 2016��10��27�� ����2:17:33
 * @version 1.0
 */
@Controller
public class HelloWeb {
	@RequestMapping(value = "/hello.do", method = RequestMethod.GET)
	@ResponseBody
	public A sayHello() {
		A a = new A();
		a.setMessage("����!");
		return a;
	}

	class A {
		private String message;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
}
