package com.bookproject.service.impl;

import com.bookproject.dataobject.BookInfo;
import com.bookproject.dataobject.BookshelfInfo;
import com.bookproject.repository.BookInfoRepository;
import com.bookproject.repository.BookshelfInfoRepository;
import com.bookproject.service.SecondPartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/28
 **/

@Service
@Slf4j
public class SecondPartServiceImpl implements SecondPartService {

    @Autowired
    private BookInfoRepository bookInfoRepository;

    @Autowired
    private BookshelfInfoRepository bookshelfInfoRepository;

    @Override
    public List<String> findBooksOnShelf(String userId) {

        List<BookshelfInfo> bookshelfInfoList = bookshelfInfoRepository.findByUserId(userId);
        List<String> BookIds=new ArrayList<>();

        for (BookshelfInfo bookshelfInfo:bookshelfInfoList) {
            BookIds.add(bookshelfInfo.getBookId());
        }
        return BookIds;
    }

    @Override
    public List<BookInfo> findBookInfosByBookIds(List<String> BookIds) {
        List<BookInfo> bookInfoList = bookInfoRepository.findByBookIdIn(BookIds);
        return bookInfoList;
    }

    @Override
    public BookshelfInfo saveReadingRecord(BookshelfInfo bookshelfInfo) {
        return bookshelfInfoRepository.save(bookshelfInfo);
    }
}
