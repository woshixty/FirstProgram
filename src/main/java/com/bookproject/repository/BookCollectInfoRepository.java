package com.bookproject.repository;
import com.bookproject.dataobject.BookCollectInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/
public interface BookCollectInfoRepository extends JpaRepository<BookCollectInfo, String> {
    BookCollectInfo findByUserIdAndAndBookId(String userId, String bookId);
    List<BookCollectInfo> findByUserId(String userId);
}
