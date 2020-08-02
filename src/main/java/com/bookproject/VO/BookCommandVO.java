package com.bookproject.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/

@Data
public class BookCommandVO {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("command_context")
    private String commandContext;

    @JsonProperty("command_time")
    private Date commandTime;

    @JsonProperty("command_like")
    private Integer commandLike;

}
