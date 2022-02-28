package mall.web.controller__ano_member;

import com.fasterxml.jackson.databind.ObjectMapper;
import mall.common.Result;
import mall.pojo.Member;
import mall.service.MemberService;
import mall.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
// response.setContentType("application/json");        //表示响应到前端的数据是一个json数据   "text/html"

    //调用业务层方法
    MemberService memberService = new MemberServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("application/json");        //表示响应到前端的数据是一个json数据   "text/html"
        PrintWriter out = response.getWriter();

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



        //2、1判断手机号和密码是否正确
//        if("13888888888".equals(mobile)&&"123456".equals(pwd)){
//            Result result = new Result();
//            result.setFlag(true);
//            result.setData(null);
//            result.setMsg("登陆成功");
//
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(result);
//            out.print(json);
//
//
//        }else{
//
//            Result result = new Result(false,null,"登陆失败");
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(result);
//            out.print(json);
//        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}