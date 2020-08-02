package com.bookproject.service;

import com.bookproject.dataobject.BookCollectInfo;
import com.bookproject.dataobject.PostCommandInfo;
import com.bookproject.dataobject.PostInfo;
import com.bookproject.dataobject.PostLikeInfo;

import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/28
 **/
public interface ThirdPartService {

    //查找所有的帖子
    List<PostInfo> findAllPostInfo();

    //保存一个帖子
    PostInfo savePostInfo(PostInfo postInfo);

    //通过postId查找一个帖子
    PostInfo findByPostId(String postId);

    //通过 PostId 和 UserId 查找是否存在
    PostLikeInfo findByPostIdAndUserId(String postId, String userId);

    //保存帖子的评论
    PostCommandInfo savePostCommandInfo(PostCommandInfo postCommandInfo);

    //点赞帖子
    PostLikeInfo savePostLike(PostLikeInfo postLikeInfo);

    //获取帖子的评论列表
    List<PostCommandInfo> findPostCommandsByPostId(String postId);

    //获取一位用户的全部帖子
    List<PostInfo> findAllPostByUserId(String userId);

    //获取一位用户的点赞记录表
    List<PostLikeInfo> findPostLikesByUserId(String userId);

    //获取一位用户的评论列表
    List<PostCommandInfo> findPostCommandsByUserId(String userId);

    //获取一位用户的收藏书籍Id列表
    List<BookCollectInfo> findCollectBooksByUserId(String userId);

}
