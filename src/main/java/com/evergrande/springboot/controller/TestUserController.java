package com.evergrande.springboot.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.evergrande.springboot.entity.User;
import com.evergrande.springboot.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 代码生成器，参考源码测试用例：
 * <p>
 * /mybatis-plus/src/test/java/com/baomidou/mybatisplus/test/generator/MysqlGenerator.java
 */
@RestController
@RequestMapping("/user")
public class TestUserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "测试页面，用户列表", notes = "")
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public ModelAndView getUserTable() {
        ModelAndView mv = new ModelAndView("userList");
        mv.addObject("users", userService.selectList(null));
        return mv;
    }


    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public List<User> getUserList() {
        return userService.selectList(null);
    }

    /**
     * 分页 查询列表
     *
     * @return Page<User>
     */
    @ApiOperation(value = "分页获取用户列表", notes = "分页获取用户列表")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<User> getUserListByPage() {
        return userService.selectPage(new Page<User>(1, 2), null);
    }


    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        boolean b = userService.insert(user);
        return "success";
    }


    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.selectById(id);
    }


    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        boolean b = userService.updateById(user);
        return "success";
    }


    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        boolean b = userService.deleteById(id);
        return "success";
    }


    /**
     * 分页 PAGE
     *//*
    @ApiOperation(value = "分页获取用户列表",notes = "")
	@GetMapping("/test")
	public Page<User> test() {
		return userService.selectPage(new Page<User>(0, 12));
	}

	*//**
     * AR 部分测试
     *//*
	@GetMapping("/test1")
	public Page<User> test1() {
		User user = new User("testAr", 0, 1);
		System.err.println("删除所有：" + user.delete(null));
		user.setRole(111L);
		user.setTestDate(new Date());
		user.setPhone("13111110000");
		user.insert();
		System.err.println("查询插入结果：" + user.selectById().toString());
		user.setName("mybatis-plus-ar");
		System.err.println("更新：" + user.updateById());
		return user.selectPage(new Page<User>(0, 12), null);
	}

	*//**
     * 增删改查 CRUD
     *//*
	@GetMapping("/test2")
	public User test2() {
		System.err.println("删除一条数据：" + userService.deleteById(1L));
		System.err.println("deleteAll：" + userService.deleteAll());
		System.err.println("插入一条数据：" + userService.insert(new User(1L, "张三", 17, 1)));
		User user = new User("张三", 17, 1);
		boolean result = userService.insert(user);
		// 自动回写的ID
		Long id = user.getId();
		System.err.println("插入一条数据：" + result + ", 插入信息：" + user.toString());
		System.err.println("查询：" + userService.selectById(id).toString());
		System.err.println("更新一条数据：" + userService.updateById(new User(1L, "三毛", 18, 2)));
		for(int i=0;i<5;++i){
			userService.insert(new User(Long.valueOf(100+i), "张三"+i, 17+i, 1));
		}
		Page<User> userListPage = userService.selectPage(new Page<User>(1,5), new EntityWrapper<>(new User()));
		System.err.println("total="+userListPage.getTotal()+", current list size="+userListPage.getRecords().size());
		return userService.selectById(1L);
	}

	*//**
     * 插入 OR 修改
     *//*
	@GetMapping("/test3")
	public User test3() {
		userService.insertOrUpdate(new User(1L, "王五", 19, 3));
		return userService.selectById(1L);
	}

	@GetMapping("/add")
	public Object addUser(){
		User user = new User("张三", 17, 1);
		JSONObject result = new JSONObject();
		result.put("result", userService.insert(user));
		return result;
	}
	
	@GetMapping("/selectsql")
	public Object getUserBySql() {
		JSONObject result = new JSONObject();
		result.put("records", userService.selectListBySQL());
		return result;
	}*/

}
