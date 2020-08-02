package com.bookproject.service;

import com.bookproject.dataobject.UserInfo;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/
public interface FourthPartService {

    //用户注册，修改个人信息
    UserInfo saveOneUserInfo(UserInfo userInfo);

    //用户登录，验证密码
    UserInfo verifyAccount(String userId);

}
