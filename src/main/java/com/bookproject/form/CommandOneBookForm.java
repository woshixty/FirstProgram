package com.bookproject.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/

@Data
public class CommandOneBookForm {

    @NotEmpty(message = "book_id")
    private String book_id;

    @NotEmpty(message = "user_id")
    private String user_id;

    @NotEmpty(message = "command_context")
    private String command_context;


}
