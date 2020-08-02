package com.bookproject.controller;

import com.bookproject.DTO.BookMostInfo2;
import com.bookproject.DTO.BookRecord;
import com.bookproject.DTO.BookSimpleInfo;
import com.bookproject.VO.ResultVO;
import com.bookproject.dataobject.BookInfo;
import com.bookproject.dataobject.BookshelfInfo;
import com.bookproject.dataobject.TagInfo;
import com.bookproject.enums.ResultEnum;
import com.bookproject.exception.BookProjectException;
import com.bookproject.form.RecordForm;
import com.bookproject.service.FirstPartService;
import com.bookproject.service.SecondPartService;
import com.bookproject.utils.ResultVOUtile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/28
 **/

@RestController
@RequestMapping("/partTwo")
@Slf4j
public class SecondPartController {

    @Autowired
    private SecondPartService secondPartService;

    @Autowired
    FirstPartService firstPartService;

    @GetMapping("/getUserBooks")
    public ResultVO getUserBooks(@RequestParam("user_id") String userId) {

        List<String> bookIds = secondPartService.findBooksOnShelf(userId);
        List<BookInfo> bookInfoList = secondPartService.findBookInfosByBookIds(bookIds);
        List<BookSimpleInfo> bookSimpleInfoList = new ArrayList<>();

        for (BookInfo bookInfo:bookInfoList) {
            BookSimpleInfo bookSimpleInfo=new BookSimpleInfo();
            BeanUtils.copyProperties(bookInfo, bookSimpleInfo);
            bookSimpleInfoList.add(bookSimpleInfo);
        }

        return ResultVOUtile.success(bookSimpleInfoList);
    }


    @GetMapping("/getOneBookInfo")
    public ResultVO getOneBookInfo(@RequestParam("book_id") String bookId) {

        BookMostInfo2 bookMostInfo2 = new BookMostInfo2();
        BookInfo bookInfo=firstPartService.findOneBookInfo(bookId);
        BeanUtils.copyProperties(bookInfo, bookMostInfo2);
        TagInfo tagInfo=firstPartService.findBookTagInfo(bookInfo.getBookTag());
        bookMostInfo2.setTagName(tagInfo.getTagName());

        return ResultVOUtile.success(bookMostInfo2);
    }


    @PostMapping("/uploadRecords")
    public ResultVO uploadRecords(@Valid RecordForm recordForm,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            log.error("【上传阅读记录】参数发生错误，recordForm={}", recordForm);
            throw new BookProjectException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        BookshelfInfo bookshelfInfo=new BookshelfInfo();

        bookshelfInfo = firstPartService.findBookShelfByBookIdAndUserId(recordForm.getUser_id(), recordForm.getBook_id());

        if (bookshelfInfo == null) {
            return ResultVOUtile.error(1, "未找到该书籍", null);
        }

        bookshelfInfo.setMemory(recordForm.getMemory());
        secondPartService.saveReadingRecord(bookshelfInfo);
        return ResultVOUtile.success();
    }


    @GetMapping("/getRecords")
    public ResultVO getRecords(@RequestParam("book_id") String bookId,
                               @RequestParam("user_id") String userId) {

        BookshelfInfo bookshelfInfo = firstPartService
                .findBookShelfByBookIdAndUserId(userId, bookId);

        if (bookshelfInfo == null) {
            return ResultVOUtile.error(1, "未找到该书籍", null);
        }

        BookRecord bookRecord = new BookRecord();
        BookInfo bookInfo = firstPartService.findOneBookInfo(bookId);
        BeanUtils.copyProperties(bookInfo, bookRecord);
        bookRecord.setMemory(bookshelfInfo.getMemory());

        return ResultVOUtile.success(bookRecord);
    }

}
