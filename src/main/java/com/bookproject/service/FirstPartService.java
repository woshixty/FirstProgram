package com.bookproject.service;

import com.bookproject.DTO.BookSimpleInfo;
import com.bookproject.dataobject.*;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/26
 **/

public interface FirstPartService {

    //查询书城所有的书籍的简略信息
    List<BookSimpleInfo> findAllBookInfo();

    //查询一本书的详细信息
    BookInfo findOneBookInfo(String bookId);

    //查询书籍类目信息
    TagInfo findBookTagInfo(Integer tagId);

    //查询一本书的所有评论
    List<BookCommandInfo> findCommandByBookId(String bookId);

    //查询一本书下的用户列表
    List<UserInfo> findUserByUserIdList(List<String> userIdList);

    //查询一位用户的基本信息
    UserInfo findUserByUserId(String userId);

    //用户收藏一本书
    BookCollectInfo saveOneBookCollect(BookCollectInfo bookCollectInfo);

    //查询所有收藏信息
    List<BookCollectInfo> findAllBookCollectInfo();

    //通过userId和bookId查询一条收藏信息
    BookCollectInfo findBookCollectInfoByUserIdAndBookId(String userId, String bookId);

    //保存一本书的信息
    BookInfo saveOneBookInfo(BookInfo bookInfo);

    //获取书籍类目列表
    List<TagInfo> findAllTagInfo();

    //获取一种类别的全部书籍的简略信息
    List<BookInfo> findBooksByTagId(Integer tagId);

    //保存一本书的评论
    BookCommandInfo saveOneBookCommand(BookCommandInfo bookCommandInfo);

    //将一本书加入书架
    BookshelfInfo addOneBookToShelf(BookshelfInfo bookshelfInfo);

    //查询一位用户所有的书架信息
    List<BookshelfInfo> findAllShelfInfo(String userId);

    //通过book_Id和user_Id查询bookshelf_info
    BookshelfInfo findBookShelfByBookIdAndUserId(String userId, String bookId);

    //获取收藏量前10的书籍
    List<BookInfo> findTopTenBooks(Sort sort);

}
