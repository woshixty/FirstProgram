package com.bookproject.exception;

import com.bookproject.enums.ResultEnum;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/
public class BookProjectException extends RuntimeException{

    private Integer code;

    public BookProjectException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public BookProjectException(Integer code, String message){
        super(message);
        this.code=code;
    }
}
