package com.hbwh.xj.myblog.controller;

import com.hbwh.xj.myblog.bean.User;
import com.hbwh.xj.myblog.service.UserService;
import com.hbwh.xj.myblog.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户操作相关API")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService = null;

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户登录", notes = "")
    @PostMapping("/sessions")
    public ResponseEntity<Result> login(User user){
        //取出存放在数据库的用户密码签名
        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }

    @ApiOperation(value = "用户注销", notes = "")
    @DeleteMapping("/sessions/{userid}")
    public ResponseEntity<Result> logout(@PathVariable("userid") String userid){

        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }

    @ApiOperation(value = "用户注册", notes = "")
    @PostMapping
    public ResponseEntity<Result> register(User user){

        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }


    @ApiOperation(value = "新增用户资料", notes = "")
    @PostMapping("/data")
    public ResponseEntity<Result> addUserData(User user){

        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }

    @ApiOperation(value = "获取用户资料", notes = "")
    @GetMapping("/data/{userid}")
    public ResponseEntity<Result> getUserData(@PathVariable("userid") String userid){

        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }

    @ApiOperation(value = "修改用户资料", notes = "")
    @PutMapping("/data")
    public ResponseEntity<Result> modifyUserData(User user){

        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }


}
