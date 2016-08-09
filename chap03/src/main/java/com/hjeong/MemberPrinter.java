package com.hjeong;

/**
 * Created by hyojeongyoon on 2016. 8. 9..
 */
public class MemberPrinter {
    public void print(Member member) {
        System.out.printf("외원 정보: 아이디%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                member.getId(), member.getEmail(), member.getName(), member.getRegisterDate());
    }
}
