package mall.web.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import mall.common.Result;
import mall.pojo.Category;
import mall.pojo.Member;
import mall.service.CategoryService;
import mall.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryServiceImpl();


    public void findAll(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {

       /* List<Category> categoryList = categoryService.findAll();

        Result result = new Result();
        result.setFlag(true);
        result.setMsg("查询成功");
        result.setData(categoryList);


        //提取类，基类中实现
        writeJson(response,result);*/
       response.setContentType("application/json");

       String allByRedis = categoryService.findAllByRedis();//返回的是json数据

        response.getWriter().print(allByRedis);
    }

}
