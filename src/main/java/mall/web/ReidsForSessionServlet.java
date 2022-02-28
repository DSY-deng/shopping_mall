package mall.web;


import mall.utils.JedisUtil;
import mall.utils.UuidUtil;
import mall.web.controller.BaseServlet;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/redis/*")
public class ReidsForSessionServlet extends BaseServlet {

    public void session(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");

        //获取浏览器传过来的cookie
        Cookie[] cookies = request.getCookies();
        //代表存有sessionid的cookie  sessionid: 01eec9db6b2b40c0ad7b754828bae303
        Cookie sessionidCookie= null;
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if("sessionid".equals(cookie.getName())){
                    sessionidCookie= cookie;
                }
            }
        }
        Jedis jedis = JedisUtil.getJedis();

        if(sessionidCookie==null){

            //这是一次新的会话
            //产生一个sessionid
            String sessionID = UuidUtil.getUuid();
            sessionidCookie = new Cookie("sessionid",sessionID);

            jedis.set(sessionID,request.getParameter("name"));



        }else{

            String sessionid = sessionidCookie.getValue();
            String sessionvalue = jedis.get(sessionid);
            response.getWriter().print(sessionvalue);

        }
        response.addCookie(sessionidCookie);

    }
}

