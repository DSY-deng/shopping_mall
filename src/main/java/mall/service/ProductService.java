package mall.service;

import mall.common.PageBean;
import mall.pojo.Product;

import java.util.List;

public interface ProductService {


    public List<Product> productAll(int cateid);


    //根据商品分类进行查询
//    public List<Product> findByCateId(int cateid);
    //分页查询
    //根据商品分类进行分页查询
    public PageBean<Product> findPage(int cateid, int currentPage, int pageSize);

    //根据id查询
    public Product findById(int id);

    void add(Product product);
}
