package com.nt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nt.pojo.User;
import com.nt.service.UserService;
import com.nt.util.RanCode;
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
    UserService userService;
	@RequestMapping("login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("before/login");
		return mav;
    }
    /**
	 * 获取生成验证码显示到 UI 界面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("checkCode")
	public void checkCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		//设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RanCode randomValidateCode = new RanCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	@RequestMapping("userLogin")
    public ModelAndView userLogin(User user,String code,HttpServletRequest request){
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		String verifyCode= (String) session.getAttribute("randomcode_key");
        if(!code.equalsIgnoreCase(verifyCode)){
        	mav.setViewName("before/login");
        	mav.addObject("msg","验证码错误！");
        	return mav;
        }
		User us=userService.login(user);
		if(us!=null) {
			int id=us.getUserid();
			mav.setViewName("before/index");
			session.setAttribute("id",id);
			mav.addObject("msg","");
		}else {
			mav.setViewName("before/login");
			if(user.getUseremail()!=""&&user.getUseremail()!=null) {
				mav.addObject("msg","登录信息错误！");
			}
		}
		return mav;
    }
	@RequestMapping("register")
    public ModelAndView register(){
        ModelAndView mav = new ModelAndView("before/register");
		return mav;
    }
	@RequestMapping("userRegister")
	public ModelAndView userRegister(User user,HttpServletRequest request){
		int i=userService.register(user);
		String msg="";
		ModelAndView mav = new ModelAndView();
		if(i==0) {
			msg="用户名已存在！";
			mav.setViewName("before/register");
		}else {
			msg="注册成功！";
			mav.setViewName("before/login");
		}
		mav.addObject("msg",msg);
		return mav;
	}
}
