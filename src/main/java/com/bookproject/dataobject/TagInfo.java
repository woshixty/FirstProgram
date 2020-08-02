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
public class TagInfo {

    @Id
    private Integer tagId;

    private String tagName;

    private String tagPhoto;

}
