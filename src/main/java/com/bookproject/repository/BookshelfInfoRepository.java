package com.bookproject.repository;

import com.bookproject.dataobject.BookshelfInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/
public interface BookshelfInfoRepository extends JpaRepository<BookshelfInfo, String> {
    List<BookshelfInfo> findByUserId(String userId);
    BookshelfInfo findByUserIdAndBookId(String userId, String bookId);
}
