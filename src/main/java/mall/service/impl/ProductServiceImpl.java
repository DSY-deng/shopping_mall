package mall.service.impl;

import mall.common.PageBean;
import mall.dao.ProductDao;
import mall.dao.impl.ProductDaoImpl;
import mall.pojo.Product;
import mall.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productdao = new ProductDaoImpl();

    @Override
    public List<Product> productAll(int cateid) {

        return productdao.productAll(cateid);
    }

    //分页查询

    @Override
    public PageBean<Product> findPage(int cateid, int currentPage, int pageSize) {



        //获取当前分类的总记录数
        long totalCount = productdao.findTotalCount(cateid);
        //获取总页数
        long totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;

        if(currentPage<=0){   //如果当前台传过来的页数小于1，则让当前页变为1
            currentPage = 1;
        }

        if(currentPage>totalPage){
            //如果当前台传过来的页数大于总页数，则让当前页变为总页数
            currentPage = (int)totalPage;
        }


        //获取一个存有分页商品的集合
        //将当前页转换成一个起始位置

        int start = (currentPage-1)*pageSize;
        List<Product> productList = productdao.findByPage(cateid, start, pageSize);

        //构建一个PageBean对象
        PageBean<Product> pageBean =new PageBean<>();
        pageBean.setList(productList);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    @Override
    public Product findById(int id) {
        return productdao.findById(id);
    }

    @Override
    public void add(Product product) {
        productdao.add(product);
    }
}
