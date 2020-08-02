package com.bookproject.repository;

import com.bookproject.dataobject.PostCommandInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/28
 **/
public interface PostCommandInfoRepository extends JpaRepository<PostCommandInfo, String> {
    List<PostCommandInfo> findByPostId(String postId);

    List<PostCommandInfo> findByUserId(String userId);
}
