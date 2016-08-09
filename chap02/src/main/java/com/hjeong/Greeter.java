package com.hjeong;

/**
 * Created by hyojeongyoon on 2016. 8. 5..
 */
public class Greeter {
    private String format;

    public String greet(String guest) {
        return String.format(format, guest);
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
