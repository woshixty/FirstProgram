package com.bookproject.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/

@Data
public class BookInfoVO {

    @JsonProperty("book_id")
    private String bookId;

    @JsonProperty("book_name")
    private String bookName;

    @JsonProperty("commands")
    private List<BookCommandVO> bookCommandInfoList;

}
