package com.bookproject.controller;

import com.bookproject.DTO.BookMostInfo;
import com.bookproject.DTO.BookNumCollect;
import com.bookproject.DTO.BookSimpleInfo;
import com.bookproject.VO.BookCommandVO;
import com.bookproject.VO.BookInfoVO;
import com.bookproject.VO.ResultVO;
import com.bookproject.dataobject.*;
import com.bookproject.enums.ResultEnum;
import com.bookproject.exception.BookProjectException;
import com.bookproject.form.AddBookToShelfForm;
import com.bookproject.form.CollectOneBookForm;
import com.bookproject.form.CommandOneBookForm;
import com.bookproject.service.FirstPartService;
import com.bookproject.utils.KeyUtil;
import com.bookproject.utils.ResultVOUtile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/26
 **/

@RestController
@RequestMapping("/partOne")
@Slf4j
public class FirstPartController {

    @Autowired
    private FirstPartService firstPartService;

    @GetMapping("/getAllBook")
    public ResultVO  getAllBook(){

        List<BookSimpleInfo> bookSimpleInfoList=firstPartService.findAllBookInfo();

        return ResultVOUtile.success(bookSimpleInfoList);
    }

    @GetMapping("/getOneBookInfo")
    public ResultVO getOneBookInfo(@RequestParam("book_id") String bookId){

        BookMostInfo bookMostInfo=new BookMostInfo();
        BookInfo bookInfo=firstPartService.findOneBookInfo(bookId);
        BeanUtils.copyProperties(bookInfo, bookMostInfo);
        TagInfo tagInfo=firstPartService.findBookTagInfo(bookInfo.getBookTag());
        bookMostInfo.setTagName(tagInfo.getTagName());

        return ResultVOUtile.success(bookMostInfo);
    }

    @GetMapping("/getOneBookCommands")
    public ResultVO getOneBookCommands(@RequestParam("book_id") String bookId){

        BookInfo bookInfo=firstPartService.findOneBookInfo(bookId);
        BookInfoVO bookInfoVO=new BookInfoVO();

        bookInfoVO.setBookName(bookInfo.getBookName());
        bookInfoVO.setBookId(bookInfo.getBookId());

        List<BookCommandInfo>bookCommandList=firstPartService.findCommandByBookId(bookId);

        List<BookCommandVO> bookCommandInfoVOList = new ArrayList<>();

        for (BookCommandInfo bookCommand:bookCommandList) {

            BookCommandVO bookCommandInfoVO=new BookCommandVO();

//            bookCommandInfoVO.setUserId(bookCommand.getUserId());
//            bookCommandInfoVO.setCommandContext(bookCommand.getCommandContext());
//            bookCommandInfoVO.setCommandTime(bookCommand.getCommandTime());
//            bookCommandInfoVO.setCommandLike(bookCommand.getCommand_like());
            BeanUtils.copyProperties(bookCommand, bookCommandInfoVO);

            UserInfo userInfo=firstPartService.findUserByUserId(bookCommand.getUserId());
            bookCommandInfoVO.setUserName(userInfo.getUserName());

            bookCommandInfoVOList.add(bookCommandInfoVO);
        }

        bookInfoVO.setBookCommandInfoList(bookCommandInfoVOList);

        return ResultVOUtile.success(bookInfoVO);
    }

    @PostMapping("/collectOneBook")
    public ResultVO collectOneBook(@Valid CollectOneBookForm collectOneBookForm,
                                   BindingResult bindingResult) {

        BookNumCollect bookNumCollect=new BookNumCollect();

        if (bindingResult.hasErrors()){
            log.error("【收藏书籍】参数发生错误，collectOneBookForm={}", collectOneBookForm);
            throw new BookProjectException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        BookCollectInfo bookCollectInfoTest = firstPartService.findBookCollectInfoByUserIdAndBookId(
                collectOneBookForm.getUser_id(), collectOneBookForm.getBook_id()
        );

        if (bookCollectInfoTest != null) {
            return ResultVOUtile.error(1, "该用户已收藏本书",null);
        }

        BookInfo bookInfo=firstPartService.findOneBookInfo(collectOneBookForm.getBook_id());
        Integer bookCollectNum=new Integer(bookInfo.getBookNumCollect()+1);


        BookCollectInfo bookCollectInfo=new BookCollectInfo();
        bookCollectInfo.setBookCollectId(KeyUtil.genUniqueKey());
        bookCollectInfo.setBookId(collectOneBookForm.getBook_id());
        bookCollectInfo.setUserId(collectOneBookForm.getUser_id());
        bookCollectInfo.setCollectStatus(0);
        firstPartService.saveOneBookCollect(bookCollectInfo);


        bookNumCollect.setBookNumCollect(bookCollectNum);
        firstPartService.saveOneBookInfo(bookInfo);


        return ResultVOUtile.success(bookCollectNum);
    }

    @GetMapping("/getAllTagInfo")
    public ResultVO getAllTagInfo() {
        List<TagInfo> tagInfoList = firstPartService.findAllTagInfo();
        return ResultVOUtile.success(tagInfoList);
    }

    @GetMapping("/getBooksByTag")
    public ResultVO getBooksByTag(@RequestParam("tag_id") Integer tagId){
        List<BookInfo> bookInfoList = firstPartService.findBooksByTagId(tagId);
        List<BookSimpleInfo> bookSimpleInfoList = new ArrayList<>();

        for (BookInfo bookInfo: bookInfoList) {
            BookSimpleInfo bookSimpleInfo=new BookSimpleInfo();
            BeanUtils.copyProperties(bookInfo, bookSimpleInfo);
            bookSimpleInfoList.add(bookSimpleInfo);
        }

        return ResultVOUtile.success(bookSimpleInfoList);

    }

    @PostMapping("/saveOneBookCommand")
    public ResultVO saveOneBookCommand(@Valid CommandOneBookForm commandOneBookForm,
                                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            log.error("【收藏书籍】参数发生错误，collectOneBookForm={}", commandOneBookForm);
            throw new BookProjectException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        BookCommandInfo bookCommandInfo=new BookCommandInfo();
        bookCommandInfo.setCommandId(KeyUtil.genUniqueKey());
        bookCommandInfo.setUserId(commandOneBookForm.getUser_id());
        bookCommandInfo.setBookId(commandOneBookForm.getBook_id());
        bookCommandInfo.setCommandContext(commandOneBookForm.getCommand_context());
        bookCommandInfo.setCommandStatus(0);
        bookCommandInfo.setCommand_like(0);

        bookCommandInfo=firstPartService.saveOneBookCommand(bookCommandInfo);

        BookInfo bookInfo=firstPartService.findOneBookInfo(commandOneBookForm.getBook_id());
        bookInfo.setBookNumCommand(bookInfo.getBookNumCommand()+1);
        firstPartService.saveOneBookInfo(bookInfo);

        return ResultVOUtile.success(bookCommandInfo);
    }

    @PostMapping("/addBookToBookShelf")
    public ResultVO addBookToBookShelf(@Valid AddBookToShelfForm addBookToShelfForm,
                                       BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            log.error("【书籍加入书架】参数发生错误，addBookToShelfForm={}", addBookToShelfForm);
            throw new BookProjectException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        BookshelfInfo result=new BookshelfInfo();
        result=firstPartService.findBookShelfByBookIdAndUserId(
                addBookToShelfForm.getUser_id(), addBookToShelfForm.getBook_id()
        );

        if (result != null ) {
            return ResultVOUtile.error(1, "书籍已经存在于书架", null);
        }

        BookshelfInfo bookshelfInfo=new BookshelfInfo();
        bookshelfInfo.setMemory(0);
        bookshelfInfo.setBookStatus(0);
        bookshelfInfo.setUserId(addBookToShelfForm.getUser_id());
        bookshelfInfo.setBookId(addBookToShelfForm.getBook_id());
        bookshelfInfo.setBookshelfId(KeyUtil.genUniqueKey());

        firstPartService.addOneBookToShelf(bookshelfInfo);
        return ResultVOUtile.success();
    }
    @GetMapping("/getTop10Books")
    public ResultVO getTop10Books() {

        Sort sort = new Sort(Sort.Direction.DESC, "bookNumCollect");
        List<BookInfo> bookInfoList = firstPartService.findTopTenBooks(sort);

        if(bookInfoList.size()<10) {
            return ResultVOUtile.success(bookInfoList);
        }

        return ResultVOUtile.success(bookInfoList.subList(0, 9));

    }

    @GetMapping("/test")
    public String test(){
        return "hello";
    }

}
