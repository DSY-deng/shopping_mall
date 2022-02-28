package mall.service;

import mall.pojo.Orders;

public interface OrderService {

    //添加订单的同时，添加订单项
    public void add(Orders order, String[] ids, String[] amounts);

    //根据订单号修改订单的状态
    public void update(int status,String orderNumber);
}