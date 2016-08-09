package com.hjeong;

import java.util.Date;

/**
 * Created by hyojeongyoon on 2016. 8. 9..
 */
public class MemberRegisterService {
    private MemberDao memberDao;

    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void regist(RegisterRequest request) {
        Member member = memberDao.selectByEmail(request.getEmail());
        if (member != null) {
            throw new AlreadyExistingMemberException(member.getEmail() + " 으로 가입된 회원이 존재합니다.");
        }

        Member newMember = new Member(request.getEmail(),
                request.getPassword(),
                request.getName(),
                new Date());
        memberDao.insert(newMember);
    }
}
