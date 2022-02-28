package mall.dao.impl;

import mall.dao.MemberDao;
import mall.pojo.Member;
import mall.utils.DateUtil;
import mall.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class MemberDaoImpl implements MemberDao {

    //访问数据库使用QueryRunner来完成
    QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

    @Override
    public Member findeByMobileAndPwd(String mobile, String pwd) {
        String sql = "select * from `member` where mobile=? and pwd=?";
        Member member = null;
        try {
            // QueryRunner queryRunner最后回车
            member = runner.query(sql,new BeanHandler<>(Member.class),mobile,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }


    //登录
    @Override
    public Member findByMobile(String mobile) {
        String sql = "select * from `member` where mobile=?";
        Member member =null;
        try {
            member=  runner.query(sql, new BeanHandler<>(Member.class), mobile);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public void add(Member member) {
        String sql = "insert into `member` values(null,?,?,?,?,?,?,?)";

        try {
            runner.update(sql,member.getMobile(),member.getPwd(),member.getNick_name(),member.getReal_name(),member.getEmail(),member.getGender(), DateUtil.date2String(member.getRegister_time()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
