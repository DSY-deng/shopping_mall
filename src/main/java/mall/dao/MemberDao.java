package mall.dao;

import mall.pojo.Member;

public interface MemberDao {

    public Member findeByMobileAndPwd(String mobile, String pwd);

    public Member findByMobile(String mobile);

    public void add(Member member);

}
