package com.pak.controller;

import com.pak.common.ResultModel;
import com.pak.dto.UserDto;
import com.pak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lujun.chen on 2017/3/13.
 */
@RestController
@RequestMapping("/")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value="hello",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
  public ResultModel hello(){
    ResultModel result = new ResultModel();
    result.setInfo("OK");
    return result;
  }

  @RequestMapping(value="login",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
  public ResultModel findUser(@RequestParam(value = "mobile", required = false) String mobile,
                              @RequestParam(value = "password", required = false) String password,
                              @RequestParam(value = "smsCode", required = false) String smsCode){
    ResultModel result = new ResultModel();
    Map<String,Object> params = new HashMap<String,Object>();
      params.put("mobile",mobile);
      params.put("smsCode",smsCode);
      params.put("password",password);
    UserDto userDto = userService.findUserById(params);
    result.setData(userDto);
    return result;
  }

    @RequestMapping(value="register",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ResultModel addUser(@RequestBody UserDto userDto){
        ResultModel result = new ResultModel();
        userService.addUser(userDto);
        result.setData(userDto);
        return result;
    }

}
