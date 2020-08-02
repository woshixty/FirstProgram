package com.bookproject.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/

@Entity
@Data
public class BookCommandInfo {

    @Id
    private String commandId;

    private String bookId;

    private String userId;

    private String commandContext;

    private Integer commandStatus;

    private Date commandTime;

    private Integer command_like;

}
