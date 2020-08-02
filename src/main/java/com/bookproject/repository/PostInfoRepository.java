package com.bookproject.repository;

import com.bookproject.dataobject.PostInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/28
 **/
public interface PostInfoRepository extends JpaRepository<PostInfo, String> {
    List<PostInfo> findByUserId(String userId);
}
