package mall.web.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import mall.common.Result;
import mall.pojo.Member;
import mall.service.MemberService;
import mall.service.impl.MemberServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

@WebServlet("/member/*")
public class MemberServlet extends BaseServlet {

    //调用业务层方法
    MemberService memberService = new MemberServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException {
        PrintWriter out = response.getWriter();

        //接收用户穿过来的验证码
        String code = request.getParameter("code");
        //从session中取出服务器端生成的验证码
        String validatecode = (String)request.getSession().getAttribute("validatecode");

        //验证码取出后，就从session中销毁
        request.getSession().removeAttribute("validatecode");

        if(validatecode!=null&&validatecode.equalsIgnoreCase(code)){
        //1、接收用户传过来的手机号和密码
        String mobile = request.getParameter("mobile");
        String pwd = request.getParameter("pwd");

        //2.2点用业务层判断手机号和密码是否正确
        Member member = memberService.login(mobile,pwd);
        //对member对象进行判断，如果为null,表示登陆失败，如果不
        //创建一个结果的对象
        Result result =new Result();
        //null则表示登陆成功
        if(member == null) {

            result.setFlag(false);
            result.setMsg("登陆失败");

        }else{
            //登陆判断后操作
            //如果登陆成功,需要将用户的信息保存到session中
            HttpSession session = request.getSession();
            session.setAttribute("member",member);


            result.setFlag(true);
            result.setMsg("登陆成功");
        }
        //将结果对象装换成json
        ObjectMapper mapper = new ObjectMapper();
        String resultJson = mapper.writeValueAsString(result);
        out.print(resultJson);
    }else{
        Result result = new Result();
        result.setMsg("验证码出错");
        result.setFlag(false);
        writeJson(response,result);
        }

    }

    public void logout(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException {
        //销毁session
        HttpSession session = request.getSession();

        session.invalidate();

        Result result = new Result();
        result.setFlag(true);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(result);

        response.getWriter().print(s);
    }

    public void register(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException {
        //如果当前台的数据很多的时候，可以使用BeanUtils.Populdate()方法快速的将表单中的数据
        //封装到实体对象中，前题要保存表单数据的名字要实体对象的属性名一致
        Map<String, String[]> parameterMap = request.getParameterMap();
        Member member =new Member();
        try {
            BeanUtils.populate(member,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //设置用户的注册时间
        member.setRegister_time(new Date());

        //System.out.println("xxxx");
        //调用业务层完成添加的任务
        boolean result = memberService.regist(member);

        //根据注册的结果返回对应的消息
        Result result1 = new Result();
        if(result==true){
            //说明注册成功
            result1.setFlag(true);
            result1.setMsg("注册成功");
        }else{
            result1.setFlag(false);
            result1.setMsg("注册失败");
        }

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(result1);

        response.getWriter().print(s);
    }

    public void findUserNikName(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException {
        HttpSession session = request.getSession();  //无则创建，有则获取
        Member member = (Member)session.getAttribute("member");   //如果已经登陆过member对象不为null,否则为null

        ObjectMapper mapper = new ObjectMapper();
        //返回的结果对象
        Result result = new Result();
        result.setFlag(true);
        result.setMsg("");
        result.setData(member);

        //转换成json
        String s = mapper.writeValueAsString(result);

        response.getWriter().print(s);
    }


}
