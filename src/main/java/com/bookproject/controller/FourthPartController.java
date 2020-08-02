package com.bookproject.controller;

import com.bookproject.DTO.UserBasicInfo;
import com.bookproject.VO.ResultVO;
import com.bookproject.dataobject.UserInfo;
import com.bookproject.enums.ResultEnum;
import com.bookproject.exception.BookProjectException;
import com.bookproject.form.UserInfoForm;
import com.bookproject.service.FirstPartService;
import com.bookproject.service.FourthPartService;
import com.bookproject.utils.ResultVOUtile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author qyyzxty@icloud.com
 * 2020/7/27
 **/

@RestController
@RequestMapping("/partFour")
@Slf4j
public class FourthPartController {

    @Autowired
    private FirstPartService firstPartService;

    @Autowired
    private FourthPartService fourthPartService;

    @PostMapping("/register")
    public ResultVO register(@Valid UserInfoForm userInfoForm,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【注册账号】，userInfoForm{}", userInfoForm);
            throw new BookProjectException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        UserInfo result=firstPartService.findUserByUserId(userInfoForm.getUser_id());

        if (result != null) {
            return ResultVOUtile.error(1, "账号已存在", null);
        }

        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(userInfoForm.getUser_id());
        userInfo.setUserPassword(userInfoForm.getUser_password());
        userInfo.setUserPersonalWord(userInfoForm.getUser_personal_word());
        userInfo.setUserName(userInfoForm.getUser_name());

        result=fourthPartService.saveOneUserInfo(userInfo);

        return ResultVOUtile.success(result);
    }

    @GetMapping("/verify")
    public ResultVO verify(@RequestParam("user_id") String userId,
                           @RequestParam("user_password") String userPassword) {

        UserInfo userInfo = fourthPartService.verifyAccount(userId);
        if (userInfo == null) {
            return ResultVOUtile.error(1, "账户不存在或者密码错误", null);
        }

        if (userInfo.getUserPassword().equals(userPassword)) {
            return ResultVOUtile.success();
        }

        return ResultVOUtile.error(1, "账户不存在或者密码错误", null);
    }

    @PostMapping("/completeInfo")
    public ResultVO completeInfo(@Valid UserInfoForm userInfoForm,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("【完善账号信息】，userInfoForm{}", userInfoForm);
            throw new BookProjectException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        UserInfo result=firstPartService.findUserByUserId(userInfoForm.getUser_id());

        if (result == null) {
            return ResultVOUtile.error(1, "账号不存在", null);
        }

        result.setUserId(userInfoForm.getUser_id());
        result.setUserPassword(userInfoForm.getUser_password());
        result.setUserPersonalWord(userInfoForm.getUser_personal_word());
        result.setUserName(userInfoForm.getUser_name());

        fourthPartService.saveOneUserInfo(result);

        return ResultVOUtile.success();
    }

    @GetMapping("/getUserInfo")
    public ResultVO getUserInfo(@RequestParam("user_id") String userId) {

        UserInfo userInfo=firstPartService.findUserByUserId(userId);

        if (userInfo == null) {
            return ResultVOUtile.error(1, "账号不存在", null);
        }

        UserBasicInfo userBasicInfo=new UserBasicInfo();
        BeanUtils.copyProperties(userInfo, userBasicInfo);
        return ResultVOUtile.success(userBasicInfo);
    }
}
