package mall.dao.impl;

import mall.dao.ProductDao;
import mall.pojo.Product;
import mall.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ProductDaoImpl implements ProductDao {

    QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());


    @Override
    public List<Product> productAll(int cateid) {
        //注意属性-----数据库中列的属性
        String sql = "select * from product where cate_id = ?";

        try {
            List<Product> productList = runner.query(sql,new BeanListHandler<>(Product.class),cateid);
            return productList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


  /*  @Override
    public List<Product> findByCateId(int cateid) {

        String sql = "select * from `product` where cate_id=?";
        try {
            List<Product> productList = runner.query(sql, new BeanListHandler<>(Product.class), cateid);
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }*/

    //分页查询
    @Override
    public long findTotalCount(int cateid)  {
        String sql ="select count(*) from product where cate_id=?";
        Long totalCount = 0L;
        try {
            totalCount = runner.query(sql, new ScalarHandler<Long>(), cateid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCount;
    }


    @Override
    public List<Product> findByPage(int cateid, int start, int pageSize) {

       // `product`
        String sql = "select * from product where cate_id=? limit ?,?";
        try {
            List<Product> productList = runner.query(sql, new BeanListHandler<>(Product.class),cateid, start, pageSize);
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product findById(int id) {
        String sql = "select * from product where id =?";
        try {
            Product  product = runner.query(sql,new BeanHandler<>(Product.class),id);
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void add(Product product) {
        String sql = "insert into product values(null,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            runner.update(sql,product.getName(),product.getCate_id(),product.getThumbnail(),product.getInventory(),product.getSales_volume(),product.getPrice(),product.getSale_price(),product.getDetail_description(),product.getSelling_description(),product.getCreate_time(),product.getSale_time());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
