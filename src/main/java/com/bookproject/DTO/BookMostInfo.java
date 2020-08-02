package com.bookproject.DTO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/26
 **/

@Data
public class BookMostInfo {

    private String bookId;

    private String bookName;

    private String bookIntro;

    private String bookCover;

    private Integer bookNumCollect;

    private Integer bookNumCommand;

    private String bookAuthor;

    private String tagName;

}
