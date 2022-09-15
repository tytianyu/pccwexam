package com.pccwexam.user.api;

import com.pccwexam.user.api.dto.ResultBean;
import com.pccwexam.user.api.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@FeignClient(value = "user-api")
@RequestMapping("/user")
public interface UserApi {

    @PostMapping("/register")
    ResultBean<UserDTO> register(@RequestBody @Valid UserDTO userDTO);

    @GetMapping("/getAll")
    ResultBean<List<UserDTO>> getAllUsers();

    @GetMapping("/get")
    ResultBean<UserDTO> getUser(@RequestParam("id") Long id);

    @PostMapping("/edit")
    ResultBean<UserDTO> editUser(@RequestBody UserDTO userDTO);

    @PostMapping("/delete")
    ResultBean deleteUser(@RequestParam("id") Long id);

    @PostMapping("/deleteAll")
    ResultBean deleteUsers(@RequestBody Set<Long> ids);

}
