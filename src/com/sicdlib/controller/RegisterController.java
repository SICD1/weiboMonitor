package com.sicdlib.controller;

import com.sicdlib.dto.Constant;
import com.sicdlib.dto.User;
import com.sicdlib.service.IParamService;
import com.sicdlib.service.IRegisterService;
import com.sicdlib.util.MD5Util.MD5Util;
import com.sicdlib.util.mailUtil.SendMails;
import com.sicdlib.util.mailUtil.ValidationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Yh on 2016/9/12.
 */
@Controller
public class RegisterController {
    @Autowired
    @Qualifier("registerService")
    IRegisterService registerService;

    @Autowired
    @Qualifier("paramService")
    IParamService paramService;

    @RequestMapping(value="register/{id}", method = RequestMethod.GET)
    public String register(@PathVariable String id){
        System.out.println(id);
        //0:跳转注册页面
        if(id.equals("0")){
            return "register";
        }
        return "login";
    }

    @RequestMapping("reg")
    public String reg(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("u_name");
        String password = MD5Util.generatePassword(req.getParameter("u_pwd"));
        String email = req.getParameter("u_email");
        String telephone = req.getParameter("u_telephone");
        String sex = req.getParameter("u_sex");

        User user = new User();
        user.setU_name(name);
        user.setU_pwd(password);
        user.setU_email(email);
        user.setU_telephone(telephone);

        String paramType = Constant.PARAM_SEX;
        int sex_value = paramService.getParamValue(paramType, sex);
        user.setU_sex(sex_value);

        boolean result = registerService.registerNormalUser(user);
        if(result) {
            return "redirect: login.jsp";
        }
        return "error";
    }

    @RequestMapping("isExistUser")
    public void isExisterUser(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String name = req.getParameter("u_name");
        boolean isExist = registerService.isExistUser(name);
        PrintWriter out = resp.getWriter();
        if(!isExist) {
            out.print("success");
        } else {
            out.print("failure");
        }
    }
    @RequestMapping("sendMail")
    public void sendMail(HttpServletRequest req) throws MessagingException {
        System.out.println("已经加载...");
        String u_mail = req.getParameter("u_mail");
        StringBuffer stringBuffer = new StringBuffer("验证码<br>");
        String code = ValidationCode.getValidationCode(4);
        System.out.println("code: " + code);
        stringBuffer.append(code);
        req.getSession().setAttribute("valication_mail_code", code);
        SendMails.sendMail(u_mail, stringBuffer.toString());
    }

}
