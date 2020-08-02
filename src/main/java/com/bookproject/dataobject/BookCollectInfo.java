package com.bookproject.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/

@Entity
@Data
public class BookCollectInfo {

    @Id
    private String bookCollectId;

    private String bookId;

    private String userId;

    private Integer collectStatus;

}
