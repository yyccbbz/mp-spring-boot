package com.evergrande.springboot.controller;

import com.evergrande.springboot.common.AjaxResult;
import com.evergrande.springboot.entity.User;
import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.evergrande.springboot.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 登录 （注解跳过权限验证）
     */
//    @Login(action = Action.Skip)
//    @RequestMapping(value = {"/login"})
//    public String login() {
//        SSOToken st = SSOHelper.getToken(request);
//        if (st != null) {
//            return redirectTo("/index");
//        }
//        return "/login";
//    }

    /**
     * 登录 （注解跳过权限验证）
     */
    @Login(action = Action.Skip)
    @ResponseBody
    @RequestMapping("/loginpost")
    public AjaxResult loginpost() {
        AjaxResult ajaxResult = new AjaxResult();

        //生产环境需要过滤sql注入
        WafRequestWrapper req = new WafRequestWrapper(request);
        String loginname = req.getParameter("loginname");
        String password = req.getParameter("password");

        System.err.println("loginname = " + loginname);
        System.err.println("password = " + password);

        if (StringUtils.isNotEmpty(loginname) && StringUtils.isNotEmpty(password)) {
            User u = new User();
            u.setLoginName(loginname);
            u.setPassword(password);
            User selectOne = userService.selectOne(new EntityWrapper<>(u));
            if (selectOne != null) {
                //authSSOCookie 设置 cookie 同时改变 jsessionId
                SSOToken st = new SSOToken(request);
                st.setId(12306L);//主键 ID
                st.setUid(selectOne.getId().toString());//用户 ID
                st.setType(1);//登录类型

                //记住密码，设置 cookie 时长 1 周 = 604800 秒 【动态设置 maxAge 实现记住密码功能】
                String rememberMe = req.getParameter("rememberMe");
                if ( "true".equals(rememberMe) ){
                	request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 604800);
                }
                SSOHelper.setSSOCookie(request, response, st, true);

                /**
                 * 登录需要跳转登录前页面，自己处理 ReturnURL 使用
                 * HttpUtil.decodeURL(xx) 解码后重定向
                 */
                ajaxResult.setCode(1).setMsg("/index.html").setObj(st);
                return ajaxResult;
            }
        }
        ajaxResult.setCode(0).setMsg("用户名或密码错误").setObj("/login");
        return ajaxResult;
    }

}
