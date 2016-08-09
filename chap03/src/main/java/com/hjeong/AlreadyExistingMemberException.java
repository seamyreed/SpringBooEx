package com.hjeong;

/**
 * Created by hyojeongyoon on 2016. 8. 9..
 */
public class AlreadyExistingMemberException extends RuntimeException {
    public AlreadyExistingMemberException(String message) {
        super(message);
    }
}
