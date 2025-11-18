package com.learnflix.exception;

import com.learnflix.entity.User;

public class UsernameOrPasswordInvalidException extends RuntimeException{

    public UsernameOrPasswordInvalidException(String message){
        super(message);
    }

}
