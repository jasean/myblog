package com.hbwh.xj.myblog.controller;

import com.hbwh.xj.myblog.bean.User;
import com.hbwh.xj.myblog.service.UserService;
import com.hbwh.xj.myblog.util.result.ResponseResult;
import com.hbwh.xj.myblog.util.result.Result;
import com.hbwh.xj.myblog.util.result.ResultCode;
import com.hbwh.xj.myblog.util.tool.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public ResponseEntity<Result> login(HttpServletRequest request, HttpServletResponse response,
                                        User user){
        //取出存放在数据库的用户密码签名
        User uInDb = userService.getUserByUserid(user.getUserid());
        if(uInDb == null){
            return new ResponseEntity<Result>(Result.failure(ResultCode.USER_NOT_EXIST),
                    HttpStatus.OK);
        }
        String digestInDb = uInDb.getPassword();

        //校验密码
        boolean result = MD5Utils.verifyMD5(user.getPassword(), digestInDb);
        if(false == result){
            return new ResponseEntity<Result>(Result.failure(ResultCode.USER_LOGIN_ERROR), HttpStatus.OK);
        }

        //保存session
        HttpSession session = request.getSession();
        if (session.isNew()){
            session.setAttribute("user", uInDb);

            //添加cookie
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            response.addCookie(cookie);
        }

        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }

    @ApiOperation(value = "用户注销", notes = "")
    @DeleteMapping("/sessions/{userid}")
    public ResponseEntity<Result> logout(@PathVariable("userid") String userid,
                                         HttpServletRequest request){

        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("user") != null){
            session.invalidate();
        }
        return ResponseResult.get().resultCode(ResultCode.SUCCESS).build();
    }


    @ApiOperation(value = "用户注册", notes = "")
    @PostMapping
    public ResponseEntity<Result> register(User user){

        String password = user.getPassword();
        password = MD5Utils.md5BySalt(password);
        user.setPassword(password);

       boolean result = userService.addUser(user);
       if(result == true){
           return new ResponseEntity(Result.success(), HttpStatus.OK);
       }

       return ResponseResult.get().resultCode(ResultCode.USER_ERROR_REGISTER).build();
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
