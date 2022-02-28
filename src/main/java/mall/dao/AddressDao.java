package mall.dao;


import mall.pojo.Address;

import java.util.List;

public interface AddressDao {

    public List<Address> findByMemberId(int memberid);

    //将当前会员的地址的默认值修改为0
    public void updateDefaultValue(int memberid);

    public void add(Address address);

    Address findById(int id);
}
