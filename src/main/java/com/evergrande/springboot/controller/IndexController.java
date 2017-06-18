package com.evergrande.springboot.controller;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 */
@Controller
public class IndexController extends BaseController {

	/**
	 * <p>
	 * SSOHelper.getToken(request)
	 * 
	 * 从 Cookie 解密 token 使用场景，拦截器
	 * </p>
	 * 
	 * <p>
	 * SSOHelper.attrToken(request)
	 * 
	 * 非拦截器使用减少二次解密
	 * </p>
	 */
//	@Permission("1000")
//	@RequestMapping("/index")
//	public String index(Model model) {
//		SSOToken st = SSOHelper.attrToken(request);
//		if (st != null) {
//			System.err.println(" Long 类型 ID: " + st.getId());
//			model.addAttribute("userId", st.getUid());
//			System.err.println(" 启动注入测试模式：" + SSOConfig.getInstance().getRunMode());
//		}
//		return "index";
//	}
	
	/**
	 * 验证码 （注解跳过权限验证）
	 */
//	@Login(action = Action.Skip)
//	@ResponseBody
//	@RequestMapping("/verify")
//	public void verify() {
//		try {
//			String verifyCode = CaptchaUtil.outputImage(response.getOutputStream());
//			System.out.println("验证码:" + verifyCode);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 异常 404 提示页
	 */
	@RequestMapping("/404")
	public String error_404() {
		return "error/404";
	}

}
