package mall.service.impl;

import mall.dao.MemberDao;
import mall.dao.impl.MemberDaoImpl;
import mall.pojo.Member;
import mall.service.MemberService;

public class MemberServiceImpl implements MemberService {

   MemberDao memberDao= new MemberDaoImpl();

    @Override
    public Member login(String mobile, String pwd) {

        Member member = memberDao.findeByMobileAndPwd(mobile,pwd);
        return member;
    }

    @Override
    public boolean regist(Member member) {
        //判断手机号是否存在
        Member mobile = memberDao.findByMobile(member.getMobile());
        if(mobile!=null){   //如果存存则返加null
            return false;
        }

        //如果不存在则完成添加的操作
        memberDao.add(member);
        return true;
    }
}
