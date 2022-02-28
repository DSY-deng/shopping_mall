package mall.dao.impl;

import mall.dao.ItemDao;
import mall.pojo.Item;
import mall.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class ItemDaoImpl implements ItemDao {
    QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());


    @Override
    public void add(Item item) {
        String sql = "insert into item values(null,?,?,?,?,?)";
        try {
            runner.update(sql,item.getOrder_id(),item.getProduct_id(),item.getAmount(),item.getTotal_price(),item.getPayment_price());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}