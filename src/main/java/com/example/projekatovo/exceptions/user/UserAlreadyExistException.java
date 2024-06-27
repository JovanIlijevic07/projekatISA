package com.example.projekatovo.exceptions.user;

import com.example.projekatovo.exceptions.BaseException;

public class UserAlreadyExistException extends BaseException {
    public UserAlreadyExistException(String message) {
        super(message);}
}
