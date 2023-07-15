package com.example.demooooo.Controller;


import com.example.demooooo.dto.UserDTO;
import com.example.demooooo.service.UserService;
import com.example.demooooo.utility.StringUtil;
import com.example.demooooo.vo.Result;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
@CrossOrigin(exposedHeaders = "key,token")
@RestController
public class HelloController {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @GetMapping("test")
    public String test() {
        return "Hello World";
    }
    @GetMapping("sendSMS")
    public ResponseEntity<Result>sendSMS(String phone) {
        String code = StringUtil.randomNumber(4);
        //生成一个key值
        String key = StringUtil.uuid();
        //调用redis的方法将 key 和value 存入redis   code 就是value 过期时间 五分钟
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
        System.out.println("code="+code);
        System.out.println("key="+key);
        /*
        try {
            //可能抛出异常的代码
            SendSmsResponseBody sendSmsResponseBody = SMSUtil.sendSMS(phone, code);
            if ("OK".equals(sendSmsResponseBody.getCode())) {

                return ResponseEntity.status(200).header("key", key).body(Result.ok("短信发送成功"));
            } else {
                return ResponseEntity.status(200).body(Result.error("短信发送失败"));
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(200).body(Result.error("短信发送失败"));
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(200).body(Result.error("短信发送失败"));
        }
        */
        return ResponseEntity.status(200).header("key", key).body(Result.ok("短信发送成功"));

    }



    @PostMapping("reg")
    public ResponseEntity<Result>reg(@Validated @RequestBody UserDTO userDTO, @RequestHeader("key")String key){
        //首先先取验证码 o就是真正的验证码
        String o = (String)redisTemplate.opsForValue().get(key);
        //判断验证码是否过期
        if (o==null){
            return ResponseEntity.status(200).body(Result.error("验证码过期"));
        }
        //验证码是否正确
        if (!o.equals(userDTO.getCode())){
            return ResponseEntity.status(200).body(Result.error("验证码错误"));
        }
        boolean success = userService.reg(userDTO);
        return success ? ResponseEntity.status(200).body(Result.ok("注册成功")) : ResponseEntity.status(200).body(Result.error("注册失败"));


    }
    @GetMapping("imageCode")
    public ResponseEntity<Result>imageCode(){
        // 获取图形验证码的内容
        String text = defaultKaptcha.createText();
        System.out.println("img code="+text);
        //保存下来的 text 放入 redis里面，便于登录的时候进行对比
        String key = StringUtil.uuid();
        System.out.println("img key="+key);
        //存入redis
        redisTemplate.opsForValue().set(key,text,5,TimeUnit.MINUTES);
        //生成一张图片
        BufferedImage image = defaultKaptcha.createImage(text);
        //将图片转换成base64加密的字符串
        String s = StringUtil.changeImage2String(image);
        return  ResponseEntity.status(200).header("key",key).body(Result.ok("成功",s));




    }
    @PostMapping("login")
    public ResponseEntity<Result>login(@Validated @RequestBody UserDTO userDTO, @RequestHeader("key")String key){
        //获取图形验证码  通过key
        String o = (String)redisTemplate.opsForValue().get(key);
        if (o==null){
            return ResponseEntity.status(200).body(Result.error("验证码过期"));
        }
        if (!o.equals(userDTO.getCode())){
            return ResponseEntity.status(200).body(Result.error("验证码输入错误"));
        }
        String login = userService.login(userDTO);

        return ResponseEntity.status(200).body(Result.ok("登陆成功"));


    }



}



