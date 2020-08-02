package com.bookproject.service.impl;

import com.bookproject.dataobject.UserInfo;
import com.bookproject.repository.UserInfoRepository;
import com.bookproject.service.FirstPartService;
import com.bookproject.service.FourthPartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/
@Service
@Slf4j
public class FourthPartServiceImpl implements FourthPartService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo saveOneUserInfo(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo verifyAccount(String userId) {
        return userInfoRepository.findOne(userId);
    }
}
