package com.evergrande.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.evergrande.springboot.entity.User;
import com.evergrande.springboot.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-10
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "系统用户列表", notes = "")
    @ResponseBody
    @RequestMapping("list")
    public String list() {
        List<User> list = userService.selectList(null);
        return JSON.toJSONString(list);
    }

    @ResponseBody
    @RequestMapping("add")
    public String add(@RequestParam("jsonstr") String jsonstr) {

        System.out.println("jsonstr = " + jsonstr);

        User user = new User();
        System.out.println("user = " + JSON.toJSONString(user));

        boolean b = userService.insert(user);

        return JSON.toJSONString(b);
    }



}
