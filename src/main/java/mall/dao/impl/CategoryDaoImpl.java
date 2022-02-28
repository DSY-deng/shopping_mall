package mall.dao.impl;

import mall.dao.CategoryDao;
import mall.pojo.Category;
import mall.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.annotation.Annotation;
import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl  implements CategoryDao {

    QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

    @Override
    public List<Category> findAll() {
        System.out.print("category......");
       String sql="select * from category";
        try {

            List<Category> categoryList = runner.query(sql,new BeanListHandler<>(Category.class));
            return categoryList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
