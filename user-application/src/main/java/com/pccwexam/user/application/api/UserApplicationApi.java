package com.pccwexam.user.application.api;

import com.pccwexam.user.api.UserApi;
import com.pccwexam.user.api.dto.ResultBean;
import com.pccwexam.user.api.dto.UserDTO;
import com.pccwexam.user.application.utils.ResultBeanUtil;
import com.pccwexam.user.domain.service.UserService;
import com.pccwexam.user.infrastructure.email.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Log4j2
@RestController
@Api(tags = "User Api")
public class UserApplicationApi implements UserApi {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @ApiOperation("Register user")
    @Override
    public ResultBean<UserDTO> register(UserDTO userDTO) {
        userDTO = userService.register(userDTO);
        if (userDTO.isExist()) {
            return ResultBeanUtil.fail("User already exist");
        }
        log.info("send email {} after register", userDTO.getEmail());
        emailService.sendRegisterEmail(userDTO.getEmail());
        return ResultBeanUtil.success(userDTO);
    }

    @ApiOperation("Get All users")
    @Override
    public ResultBean<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOS = userService.getAllUsers();
        return ResultBeanUtil.successList(userDTOS);
    }

    @ApiOperation("Get one user according by id")
    @Override
    public ResultBean<UserDTO> getUser(Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResultBeanUtil.success(userDTO);
    }

    @ApiOperation("Edit one user")
    @Override
    public ResultBean<UserDTO> editUser(UserDTO userDTO) {
        userDTO = userService.updateUser(userDTO);
        if (userDTO == null) {
            return ResultBeanUtil.fail("User not exist");
        }
        return ResultBeanUtil.success(userDTO);
    }

    @ApiOperation("Delete one user according by id")
    @Override
    public ResultBean deleteUser(Long id) {
        boolean isSuccess = userService.deleteUser(id);
        return isSuccess ? ResultBeanUtil.success() : ResultBeanUtil.fail("Delete user fail");
    }

    @ApiOperation("Delete one user according by id set")
    @Override
    public ResultBean deleteUsers(Set<Long> ids) {
        userService.deleteUsers(ids);
        return ResultBeanUtil.success();
    }

}
