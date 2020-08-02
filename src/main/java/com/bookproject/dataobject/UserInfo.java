package com.bookproject.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/

@Entity
@Data
public class UserInfo {

    @Id
    private String userId;

    private String userPassword;

    private String userName;

    private String userPersonalWord;

    private String userPhoto;

}
