package com.bookproject.repository;

import com.bookproject.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    List<UserInfo> findByUserIdIn(List<String> userIdList);
}
