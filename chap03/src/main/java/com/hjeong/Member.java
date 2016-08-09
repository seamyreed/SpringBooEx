package com.hjeong;

import java.util.Date;

/**
 * Created by hyojeongyoon on 2016. 8. 9..
 */
public class Member {
    private Long id;
    private String email;
    private String password;
    private String name;
    private Date registerDate;

    public Member(String email, String password, String name, Date registerDate) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.registerDate = registerDate;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!password.equals(oldPassword)) {
            throw new IdPasswordNotMatchingException();
        }

        this.password = newPassword;
    }

    public void setId(long id) {
        this.id = id;
    }
}
