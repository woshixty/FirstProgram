package com.bookproject.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/28
 **/

@Data
public class PostCommandVO {

    @JsonProperty("command_id")
    private String commandId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("command_context")
    private String commandContext;

    @JsonProperty("command_like")
    private Integer commandLike;

    @JsonProperty("command_time")
    private Date commandTime;


}
