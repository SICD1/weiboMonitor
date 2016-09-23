package com.sicdlib.controller;

import com.sicdlib.dto.Constant;
import com.sicdlib.dto.KeyWords;
import com.sicdlib.dto.User;
import com.sicdlib.service.IParamService;
import com.sicdlib.service.IRegisterService;
import com.sicdlib.service.imple.KeyWordsService;
import com.sicdlib.service.imple.UserService;
import com.sicdlib.util.MD5Util.MD5Util;
import com.sicdlib.util.TeleValidUtil.TeleValidCode;
import com.sicdlib.util.mailUtil.SendMails;
import com.sicdlib.util.mailUtil.ValidationCode;
import com.taobao.api.ApiException;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Autowired
    @Qualifier("keyWordsService")
    private KeyWordsService keyWordsService;
    List<KeyWords> topkeywords=null;
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
    //发送手机验证码注册：
    @RequestMapping("sendTelephoneCode")
    public void sendTelephoneCode(HttpServletRequest req) throws ApiException, JsonGenerationException, JsonMappingException, IOException{
        System.out.println("已经加载到tel..");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString("123");
        TeleValidCode.getTeleValidCode();

    }
    //注销用户
    @RequestMapping("logout")
    public String logout(HttpServletRequest req){
        System.out.println("load to logout ......");
        req.getSession().removeAttribute("user");

        return "login";
    }
    //修改个人信息
    @RequestMapping("modMyInfo")
    public String modMyInfo(HttpServletRequest req, User user, Model mode){
        User session_user = (User) req.getSession().getAttribute("user");
        System.out.println(user.getU_name());
        System.out.println(user.getU_pwd());
        System.out.println(user.getU_email());
        System.out.println(user.getU_telephone());
        if(!user.getU_pwd().equals("***")){
            session_user.setU_pwd(user.getU_pwd());
        }
        session_user.setU_name(user.getU_name());
        session_user.setU_email(user.getU_email());
        session_user.setU_telephone(user.getU_telephone());
        userService.saveOrUpdate(session_user);
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        topkeywords = keyWordsService.getTopKeyWords(12,"1","2016-07-24");
        mode.addAttribute("topkeywords", topkeywords);
        return "/WEB-INF/index";
    }


}
