package com.bookproject.service;

import com.bookproject.dataobject.BookInfo;
import com.bookproject.dataobject.BookshelfInfo;

import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/
public interface SecondPartService {

    //获取用户书架上的书籍ID列表
    List<String> findBooksOnShelf(String userId);

    //通过书籍ID列表获取书籍信息表
    List<BookInfo> findBookInfosByBookIds(List<String> BookIds);

    //储存阅读记录
    BookshelfInfo saveReadingRecord(BookshelfInfo bookshelfInfo);

}
