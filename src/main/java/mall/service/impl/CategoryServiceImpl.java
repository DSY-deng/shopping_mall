package mall.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mall.dao.CategoryDao;
import mall.dao.impl.CategoryDaoImpl;
import mall.pojo.Category;
import mall.service.CategoryService;
import mall.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao = new CategoryDaoImpl() ;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public String findAllByRedis() {

        Jedis jedis = JedisUtil.getJedis();

        //1.判断redis中是否存在有分类信息
        String categoryJSON = jedis.get("CATEGORY");
        if(categoryJSON == null||categoryJSON.length()<=0){
            //3.如果没有，则到数据库中查询，查完之后再将数据返回到redis中
            List<Category> all = categoryDao.findAll();
            ObjectMapper mapper = new ObjectMapper();

            try {
                categoryJSON = mapper.writeValueAsString(all);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("CATEGORY",categoryJSON);
            System.out.println("访问了mysql数据库");
            return categoryJSON;
        }else{
            //2.如果有，则直接返回
            System.out.println("访问了redis数据库");
            return categoryJSON;
        }


    }
}
