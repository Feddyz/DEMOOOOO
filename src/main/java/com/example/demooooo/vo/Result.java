package com.example.demooooo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



    @Data // 生成 get和set方法
    @AllArgsConstructor // 生成带参构造
    @NoArgsConstructor //生成无参构造
    @ToString //重写toString方法
    public class Result<T>{
        private Integer code;// 0 成功  1 失败

        private String message;// 失败后的提示信息

        private T data;

        public static <T> Result<T> ok (String message, T data){
            return new Result<>(0,message,data);
        }

        public static <T> Result<T> ok (String message){
            return new Result<>(0,message,null);
        }

        public static <T> Result<T> error (String message){
            return new Result<>(1,message,null);
        }

        public static Result notLogin() {
            return new Result<>(2,"当前未登录",null);
        }

        public static Result notLogin(String message) {
            return new Result<>(2,message,null);
        }
    }


