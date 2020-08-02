package com.bookproject.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/26
 **/

@Entity
@Data
public class BookInfo {

    @Id
    private String bookId;

    private String bookName;

    private String bookIntro;

    private String bookCover;

    private Integer bookTag;

    private Integer bookNumCollect;

    private Integer bookNumCommand;

    private String bookUrl;

    private String bookAuthor;

    private Integer chapterSum;

}
