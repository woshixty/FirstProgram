package com.bookproject.repository;

import com.bookproject.dataobject.PostLikeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/28
 **/
public interface PostLikeInfoRepository extends JpaRepository<PostLikeInfo, String> {
    PostLikeInfo findByPostIdAndUserId(String postId, String userId);
    List<PostLikeInfo> findByUserId(String userId);
}
