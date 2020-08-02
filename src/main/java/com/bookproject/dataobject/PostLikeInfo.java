package com.bookproject.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/28
 **/

@Entity
@Data
public class PostLikeInfo {

  @Id
  private String postLikeId;

  private String userId;

  private String postId;


}
