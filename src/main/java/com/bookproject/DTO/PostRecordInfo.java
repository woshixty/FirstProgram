package com.bookproject.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/28
 **/

@Data
public class PostRecordInfo {

    private String postId;

    private String postTitle;

    private Integer postLike;

    private String postTag;

    private Date postTime;
}
