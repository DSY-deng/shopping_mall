package mall.service;

import mall.pojo.Member;

public interface MemberService {

    public Member login(String mobile,String pwd);

    boolean regist(Member member);
}
