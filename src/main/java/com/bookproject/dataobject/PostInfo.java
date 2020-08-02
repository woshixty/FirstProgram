package com.bookproject.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/

@Entity
@Data
public class PostInfo {

  @Id
  private String postId;

  private String userId;

  private String userName;

  private String bookName;

  private String postTitle;

  private String postContent;

  private Integer postLike;

  private String postTag;

  private Integer postCommandSum;

  private Date postTime;

}
