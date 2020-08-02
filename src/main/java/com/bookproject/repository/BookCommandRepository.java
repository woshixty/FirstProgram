package com.bookproject.repository;

import com.bookproject.dataobject.BookCommandInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/
public interface BookCommandRepository extends JpaRepository<BookCommandInfo, String> {
    List<BookCommandInfo> findByBookId(String bookId);
}
