package com.hjeong;

/**
 * Created by hyojeongyoon on 2016. 8. 9..
 */
public class ChangePasswordService {
    private MemberDao memberDao;

    public ChangePasswordService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void changePassword(String email, String oldPassword, String newPassword) {
        Member member = memberDao.selectByEmail(email);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        member.changePassword(oldPassword, newPassword);
        memberDao.update(member);
    }
}
