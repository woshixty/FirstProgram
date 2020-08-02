package com.bookproject.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/

@Data
public class UserInfoForm {

    @NotEmpty(message = "user_id")
    private String user_id;

    @NotEmpty(message = "user_password")
    private String user_password;

    @NotEmpty(message = "user_name")
    private String user_name;

    @NotEmpty(message = "user_personal_word")
    private String user_personal_word;

    private String user_photo;

}
