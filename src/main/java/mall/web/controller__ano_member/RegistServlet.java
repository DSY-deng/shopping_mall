package mall.web.controller__ano_member;

import com.fasterxml.jackson.databind.ObjectMapper;
import mall.common.Result;
import mall.pojo.Member;
import mall.service.MemberService;
import mall.service.impl.MemberServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

@WebServlet("/registServlet")
public class RegistServlet extends HttpServlet {

    MemberService memberService = new MemberServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        System.out.println("xxxx");
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
