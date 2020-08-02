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
public class PostCommandInfo {

  @Id
  private String commandId;

  private String postId;

  private String userId;

  private String commandContext;

  private Integer commandStatus;

  private Integer commandLike;

  private Date commandTime;

}
