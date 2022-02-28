package mall.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的地址
        StringBuffer requestURL = req.getRequestURL();
        //获取地址中最后一根斜线的内容以获取方法的名称
        String methodName = requestURL.substring(requestURL.lastIndexOf("/")+1);

        //获取方法的方法对象
        //1.获取子类中对应的方法
        Class clz = this.getClass();
        //2.获取子类中对应的方法
        try {
            Method method = clz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeJson(HttpServletResponse response ,Object object){

       response.setContentType("applicantion/json");

        //将结果对象装换成json
        ObjectMapper mapper  = new ObjectMapper();

        try {
            String json = mapper.writeValueAsString(object);
            response.getWriter().print(json);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
