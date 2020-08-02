package com.bookproject.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/28
 **/

@Data
public class PostInfoForm {

    @NotEmpty(message = "book_name")
    private String book_name;

    @NotEmpty(message = "user_id")
    private String user_id;

    @NotEmpty(message = "post_title")
    private String post_title;

    @NotEmpty(message = "post_content")
    private String post_content;

    @NotEmpty(message = "post_tag")
    private String post_tag;


}
