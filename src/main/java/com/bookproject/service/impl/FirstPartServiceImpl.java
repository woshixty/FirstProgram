package com.bookproject.service.impl;
import com.bookproject.DTO.BookSimpleInfo;
import com.bookproject.dataobject.*;
import com.bookproject.repository.*;
import com.bookproject.service.FirstPartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/26
 **/
@Service
@Slf4j
public class FirstPartServiceImpl implements FirstPartService {

    @Autowired
    private BookshelfInfoRepository bookshelfInfoRepository;

    @Autowired
    private BookCollectInfoRepository bookCollectInfoRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private BookInfoRepository bookInfoRepository;

    @Autowired
    private TagInfoRepository tagInfoRepository;

    @Autowired
    private BookCommandRepository bookCommandRepository;

    @Override
    public List<BookSimpleInfo> findAllBookInfo() {

        List<BookInfo>bookInfoList=bookInfoRepository.findAll();

        List<BookSimpleInfo>bookSimpleInfoList=new ArrayList<>();

        for (BookInfo bookInfo:bookInfoList) {
            BookSimpleInfo bookSimpleInfo=new BookSimpleInfo();
            BeanUtils.copyProperties(bookInfo, bookSimpleInfo);
            bookSimpleInfoList.add(bookSimpleInfo);
        }
        return bookSimpleInfoList;
    }

    @Override
    public BookInfo findOneBookInfo(String bookId) {
        BookInfo bookInfo=new BookInfo();
        bookInfo=bookInfoRepository.findOne(bookId);
        return bookInfo;
    }

    @Override
    public TagInfo findBookTagInfo(Integer tagId) {
        TagInfo tagInfo=new TagInfo();
        tagInfo=tagInfoRepository.findOne(tagId);
        return tagInfo;
    }

    @Override
    public List<BookCommandInfo> findCommandByBookId(String bookId) {
        List<BookCommandInfo> bookCommandInfoList=bookCommandRepository.findByBookId(bookId);
        return bookCommandInfoList;
    }

    @Override
    public List<UserInfo> findUserByUserIdList(List<String> userIdList) {
        return userInfoRepository.findByUserIdIn(userIdList);
    }

    @Override
    public UserInfo findUserByUserId(String userId) {
        return userInfoRepository.findOne(userId);
    }

    @Override
    public BookCollectInfo saveOneBookCollect(BookCollectInfo bookCollectInfo) {
        return bookCollectInfoRepository.save(bookCollectInfo);
    }

    @Override
    public List<BookCollectInfo> findAllBookCollectInfo() {
        List<BookCollectInfo> bookCollectInfoList=bookCollectInfoRepository.findAll();
        return bookCollectInfoList;
    }

    @Override
    public BookCollectInfo findBookCollectInfoByUserIdAndBookId(String userId, String bookId) {
        return bookCollectInfoRepository.findByUserIdAndAndBookId(userId, bookId);
    }

    @Override
    public BookInfo saveOneBookInfo(BookInfo bookInfo) {
        return bookInfoRepository.save(bookInfo);
    }

    @Override
    public List<TagInfo> findAllTagInfo() {
        List<TagInfo> tagInfoList = tagInfoRepository.findAll();
        return tagInfoList;
    }

    @Override
    public List<BookInfo> findBooksByTagId(Integer tagId) {
        return bookInfoRepository.findByBookTag(tagId);
    }

    @Override
    public BookCommandInfo saveOneBookCommand(BookCommandInfo bookCommandInfo) {
        bookCommandRepository.save(bookCommandInfo);
        return bookCommandRepository.findOne(bookCommandInfo.getCommandId());
    }

    @Override
    public BookshelfInfo addOneBookToShelf(BookshelfInfo bookshelfInfo) {
        return bookshelfInfoRepository.save(bookshelfInfo);
    }

    @Override
    public List<BookshelfInfo> findAllShelfInfo(String userId) {
        List<BookshelfInfo> bookshelfInfoList = bookshelfInfoRepository.findByUserId(userId);
        return bookshelfInfoList;
    }

    @Override
    public BookshelfInfo findBookShelfByBookIdAndUserId(String userId, String bookId) {
        BookshelfInfo bookshelfInfo=bookshelfInfoRepository.findByUserIdAndBookId(userId, bookId);
        return bookshelfInfo;
    }

    @Override
    public List<BookInfo> findTopTenBooks(Sort sort) {
        return bookInfoRepository.findAll(sort);
    }

}
