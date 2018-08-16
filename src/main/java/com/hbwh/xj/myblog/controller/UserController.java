package com.hbwh.xj.myblog.controller;

import com.hbwh.xj.myblog.bean.User;
import com.hbwh.xj.myblog.service.UserService;
import com.hbwh.xj.myblog.util.result.ResponseResult;
import com.hbwh.xj.myblog.util.result.Result;
import com.hbwh.xj.myblog.util.result.ResultCode;
import com.hbwh.xj.myblog.util.tool.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Api(tags = "用户操作相关API")
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
        Cookie[] cookies = request.getCookies();
        logger.info("cookies: {}", Arrays.toString(cookies));
        HttpSession session = request.getSession();
        logger.info("/users/sessions sessionid: {}, new? {}", session.getId(), session.isNew());
        if (session.isNew()){
            session.setAttribute("user", uInDb);
            session.setMaxInactiveInterval(60);

            //添加cookie
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            response.addCookie(cookie);
        }

        return ResponseResult.get().resultCode(ResultCode.SUCCESS).build();
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
           // 更新登陆状态
           return new ResponseEntity(Result.success(), HttpStatus.OK);
       }

       return ResponseResult.get().resultCode(ResultCode.USER_ERROR_REGISTER).build();
    }


    @ApiOperation(value = "获取用户资料", notes = "")
    @GetMapping("/data/{userid}")
    public ResponseEntity<Result> getUserData(@PathVariable("userid") String userid){
        User user = userService.getUserByUserid(userid);
        user.setPassword("");
        return ResponseResult.get().resultCode(ResultCode.SUCCESS).data(user).build();
    }

    @ApiOperation(value = "获取当前用户资料", notes = "")
    @GetMapping("/data/cur")
    public ResponseEntity<Result> getCurrentUserData(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return ResponseResult.get().resultCode(ResultCode.USER_NOT_LOGGED_IN).build();
        }
        User user = (User) session.getAttribute("user");
        return ResponseResult.get().resultCode(ResultCode.SUCCESS).data(user).build();
    }

    @ApiOperation(value = "修改用户资料", notes = "")
    @PutMapping("/data")
    public ResponseEntity<Result> modifyUserData(User user){
        boolean result = userService.modifyUser(user);
        if(true == result){
            return ResponseResult.get().resultCode(ResultCode.SUCCESS).build();
        }

        return ResponseResult.get().resultCode(ResultCode.SPECIFIED_QUESTIONED_USER_NOT_EXIST)
                .build();
    }


}
