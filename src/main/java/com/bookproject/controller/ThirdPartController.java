package com.bookproject.controller;

import com.bookproject.DTO.BookSimpleInfo;
import com.bookproject.DTO.PostRecordInfo;
import com.bookproject.VO.PostCommandVO;
import com.bookproject.VO.PostInfoVO;
import com.bookproject.VO.ResultVO;
import com.bookproject.dataobject.*;
import com.bookproject.enums.ResultEnum;
import com.bookproject.exception.BookProjectException;
import com.bookproject.form.LikePostForm;
import com.bookproject.form.PostCommandForm;
import com.bookproject.form.PostInfoForm;
import com.bookproject.service.FirstPartService;
import com.bookproject.service.FourthPartService;
import com.bookproject.service.ThirdPartService;
import com.bookproject.utils.KeyUtil;
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
@RequestMapping("/partThree")
@Slf4j
public class ThirdPartController {

    @Autowired
    private ThirdPartService thirdPartService;

    @Autowired
    private FirstPartService firstPartService;

    @Autowired
    private FourthPartService fourthPartService;

    @GetMapping("/getAllPost")
    public ResultVO getAllPost() {
        List<PostInfo> postInfoList = thirdPartService.findAllPostInfo();
        return ResultVOUtile.success(postInfoList);
    }


    @PostMapping("/saveOnePost")
    public ResultVO saveOnePost(@Valid PostInfoForm postInfoForm,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            log.error("【保存帖子】参数发生错误，postInfoForm={}", postInfoForm);
            throw new BookProjectException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        PostInfo postInfo = new PostInfo();

        UserInfo userInfo = firstPartService.findUserByUserId(postInfoForm.getUser_id());

//        BeanUtils.copyProperties(postInfoForm, postInfo);

        postInfo.setBookName(postInfoForm.getBook_name());
        postInfo.setUserId(postInfoForm.getUser_id());
        postInfo.setPostTag(postInfoForm.getPost_tag());
        postInfo.setPostContent(postInfoForm.getPost_content());
        postInfo.setPostTitle(postInfoForm.getPost_title());

        postInfo.setPostId(KeyUtil.genUniqueKey());
        postInfo.setPostLike(0);
        postInfo.setPostCommandSum(0);
        postInfo.setUserName(userInfo.getUserName());
        thirdPartService.savePostInfo(postInfo);

        return ResultVOUtile.success();
    }


    @PostMapping("/likeOnePost")
    public ResultVO likeOnePost(@Valid LikePostForm likePostForm,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            log.error("【为帖子点赞】参数发生错误，likePostForm={}", likePostForm);
            throw new BookProjectException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        PostLikeInfo test =new PostLikeInfo();
        test = thirdPartService.findByPostIdAndUserId(likePostForm.getPost_id(),
                likePostForm.getUser_id());

        if (test != null) {
            return ResultVOUtile.error(1, "您已经为该帖子点赞", null);
        }

        PostInfo result = new PostInfo();
        result = thirdPartService.findByPostId(likePostForm.getPost_id());

        if (result == null) {
            return ResultVOUtile.error(2, "未查找到该帖子", null);
        }

        PostLikeInfo postLikeInfo =new PostLikeInfo();
        postLikeInfo.setPostId(likePostForm.getPost_id());
        postLikeInfo.setUserId(likePostForm.getUser_id());
        postLikeInfo.setPostLikeId(KeyUtil.genUniqueKey());
        thirdPartService.savePostLike(postLikeInfo);

        result.setPostLike(result.getPostLike()+1);
        thirdPartService.savePostInfo(result);

        return ResultVOUtile.success();

    }

    @PostMapping("/savePostCommand")
    public ResultVO savePostCommand(@Valid PostCommandForm postCommandForm,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            log.error("【保存帖子评论】参数发生错误，postCommandForm={}", postCommandForm);
            throw new BookProjectException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        PostInfo result = new PostInfo();
        result = thirdPartService.findByPostId(postCommandForm.getPost_id());
        Integer commandSum=new Integer(result.getPostCommandSum()+1);

        if (result == null) {
            return ResultVOUtile.error(1, "未查找到该帖子", null);
        }

        PostCommandInfo postCommandInfo=new PostCommandInfo();
        postCommandInfo.setCommandContext(postCommandForm.getCommand_context());
        postCommandInfo.setPostId(postCommandForm.getPost_id());
        postCommandInfo.setUserId(postCommandForm.getUser_id());
        postCommandInfo.setCommandId(KeyUtil.genUniqueKey());
        postCommandInfo.setCommandLike(0);
        postCommandInfo.setCommandStatus(0);
        thirdPartService.savePostCommandInfo(postCommandInfo);

        result.setPostCommandSum(commandSum);
        thirdPartService.savePostInfo(result);
        return ResultVOUtile.success();
    }

    @GetMapping("/getOnePostInfo")
    public ResultVO getOnePostInfo(@RequestParam("post_id") String postId) {

        PostInfo postInfo = thirdPartService.findByPostId(postId);
        PostInfoVO postInfoVO=new PostInfoVO();
        List<PostCommandVO> postCommandVOList = new ArrayList<>();

        if (postInfo == null) {
            return ResultVOUtile.error(1, "帖子不存在", null);
        }

        BeanUtils.copyProperties(postInfo, postInfoVO);
        List<PostCommandInfo> postCommandInfoList = thirdPartService.findPostCommandsByPostId(postId);

        for (PostCommandInfo postCommandInfo:postCommandInfoList) {
            PostCommandVO postCommandVO= new PostCommandVO();
            BeanUtils.copyProperties(postCommandInfo, postCommandVO);
            postCommandVO.setUserName(firstPartService.findUserByUserId(postCommandInfo.getUserId()).getUserName());
            postCommandVOList.add(postCommandVO);
        }

        postInfoVO.setPostCommandVOList(postCommandVOList);

        return ResultVOUtile.success(postInfoVO);

    }

    @GetMapping("/getPostRecord")
    public ResultVO getPostRecord(@RequestParam("user_id") String userId) {

        List<PostInfo> postInfoList = thirdPartService.findAllPostByUserId(userId);
        List<PostRecordInfo> postRecordInfoList = new ArrayList<>();

        for (PostInfo postInfo:postInfoList) {
            PostRecordInfo postRecordInfo=new PostRecordInfo();
            BeanUtils.copyProperties(postInfo, postRecordInfo);
            postRecordInfoList.add(postRecordInfo);
        }

        return ResultVOUtile.success(postRecordInfoList);
    }

    @GetMapping("/getPostLikeRecord")
    public ResultVO getPostLikeRecord(@RequestParam("user_id") String userId) {

        List<PostLikeInfo> postLikeInfoList = thirdPartService.findPostLikesByUserId(userId);
        List<PostRecordInfo> postRecordInfoList = new ArrayList<>();
        PostInfo postInfo=new PostInfo();

        for (PostLikeInfo postLikeInfo:postLikeInfoList) {

            postInfo=thirdPartService.findByPostId(postLikeInfo.getPostId());

            PostRecordInfo postRecordInfo = new PostRecordInfo();
            BeanUtils.copyProperties(postInfo, postRecordInfo);
            postRecordInfoList.add(postRecordInfo);
        }
        return ResultVOUtile.success(postRecordInfoList);

    }

    @GetMapping("/getPostCommandRecord")
    public ResultVO getPostCommandRecord(@RequestParam("user_id") String userId) {

        List<PostCommandInfo> postCommandInfoList = thirdPartService.findPostCommandsByUserId(userId);
        List<PostRecordInfo> postRecordInfoList = new ArrayList<>();
        PostInfo postInfo=new PostInfo();

        for (PostCommandInfo postCommandInfo:postCommandInfoList) {

            postInfo=thirdPartService.findByPostId(postCommandInfo.getPostId());

            PostRecordInfo postRecordInfo = new PostRecordInfo();
            BeanUtils.copyProperties(postInfo, postRecordInfo);
            postRecordInfoList.add(postRecordInfo);
        }
        return ResultVOUtile.success(postRecordInfoList);

    }

    @GetMapping("/getUserCollectBooks")
    public ResultVO getUserCollectBooks(@RequestParam("user_id") String userId) {

        List<BookCollectInfo> bookCollectInfoList = thirdPartService.findCollectBooksByUserId(userId);
        List<BookSimpleInfo> bookSimpleInfoList=new ArrayList<>();

        for (BookCollectInfo bookCollectInfo:bookCollectInfoList) {

            BookSimpleInfo bookSimpleInfo=new BookSimpleInfo();
            BookInfo bookInfo=firstPartService.findOneBookInfo(bookCollectInfo.getBookId());
            BeanUtils.copyProperties(bookInfo, bookSimpleInfo);
            bookSimpleInfoList.add(bookSimpleInfo);
        }

        return ResultVOUtile.success(bookSimpleInfoList);

    }


}
