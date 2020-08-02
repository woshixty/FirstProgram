package com.bookproject.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/28
 **/
@Data
public class LikePostForm {

    @NotEmpty(message = "post_id")
    private String post_id;

    @NotEmpty(message = "user_id")
    private String user_id;

}
