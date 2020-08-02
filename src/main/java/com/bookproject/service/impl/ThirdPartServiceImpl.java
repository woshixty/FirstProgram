package com.bookproject.service.impl;

import com.bookproject.dataobject.BookCollectInfo;
import com.bookproject.dataobject.PostCommandInfo;
import com.bookproject.dataobject.PostInfo;
import com.bookproject.dataobject.PostLikeInfo;
import com.bookproject.repository.BookCollectInfoRepository;
import com.bookproject.repository.PostCommandInfoRepository;
import com.bookproject.repository.PostInfoRepository;
import com.bookproject.repository.PostLikeInfoRepository;
import com.bookproject.service.ThirdPartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/28
 **/

@Service
@Slf4j
public class ThirdPartServiceImpl implements ThirdPartService {

    @Autowired
    private PostInfoRepository postInfoRepository;

    @Autowired
    private PostLikeInfoRepository postLikeInfoRepository;

    @Autowired
    private PostCommandInfoRepository postCommandInfoRepository;

    @Autowired
    private BookCollectInfoRepository bookCollectInfoRepository;

    @Override
    public List<PostInfo> findAllPostInfo() {
        return postInfoRepository.findAll();
    }

    @Override
    public PostInfo savePostInfo(PostInfo postInfo) {
        return postInfoRepository.save(postInfo);
    }

    @Override
    public PostInfo findByPostId(String postId) {
        return postInfoRepository.findOne(postId);
    }

    @Override
    public PostLikeInfo findByPostIdAndUserId(String postId, String userId) {
        return postLikeInfoRepository.findByPostIdAndUserId(postId, userId);
    }

    @Override
    public PostCommandInfo savePostCommandInfo(PostCommandInfo postCommandInfo) {
        return postCommandInfoRepository.save(postCommandInfo);
    }

    @Override
    public PostLikeInfo savePostLike(PostLikeInfo postLikeInfo) {
        return postLikeInfoRepository.save(postLikeInfo);
    }

    @Override
    public List<PostCommandInfo> findPostCommandsByPostId(String postId) {
        return postCommandInfoRepository.findByPostId(postId);
    }

    @Override
    public List<PostInfo> findAllPostByUserId(String userId) {
        return postInfoRepository.findByUserId(userId);
    }

    @Override
    public List<PostLikeInfo> findPostLikesByUserId(String userId) {
        return postLikeInfoRepository.findByUserId(userId);
    }

    @Override
    public List<PostCommandInfo> findPostCommandsByUserId(String userId) {
        return postCommandInfoRepository.findByUserId(userId);
    }

    @Override
    public List<BookCollectInfo> findCollectBooksByUserId(String userId) {
        return bookCollectInfoRepository.findByUserId(userId);
    }
}
