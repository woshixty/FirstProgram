package com.bookproject.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/28
 **/

@Data
public class PostInfoVO {


    @JsonProperty("post_id")
    private String postId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("book_name")
    private String bookName;

    @JsonProperty("post_title")
    private String postTitle;

    @JsonProperty("post_content")
    private String postContent;

    @JsonProperty("post_like")
    private Integer postLike;

    @JsonProperty("post_tag")
    private String postTag;

    @JsonProperty("post_command_sum")
    private Integer postCommandSum;

    @JsonProperty("post_time")
    private Date postTime;

    @JsonProperty("commands")
    List<PostCommandVO> postCommandVOList;

}
