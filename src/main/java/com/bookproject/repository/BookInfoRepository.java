package com.bookproject.repository;

import com.bookproject.dataobject.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/26
 **/
public interface BookInfoRepository extends JpaRepository<BookInfo, String> {

    List<BookInfo> findByBookTag(Integer bookTag);

    List<BookInfo> findByBookIdIn(List<String> bookIds);

}
