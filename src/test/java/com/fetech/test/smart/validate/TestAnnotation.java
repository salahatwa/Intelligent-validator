package com.fetech.test.smart.validate;

import com.fetech.test.smart.validate.model.User;
import com.Intelligent.exception.ValidationException;
import com.Intelligent.validate.ValidateUtils;

import org.junit.Test;

/**
 * Created by ZhangGang on 2017/9/8.
 */
public class TestAnnotation {

    @Test
    public void mm() {
        User user = new User();
        user.setName("aaaa");
        user.setAge(18);
        ValidateUtils.check(user);
        ValidateUtils.is(user.getName()).min(2).max(20);

        ValidateUtils.check(user).and(user.getName()).min(2,"a").max(20,"b");





        try {
            //....
            ValidateUtils.check(user);
            //.....
        }catch (ValidationException e){
            throw e;
        }catch (Exception e){
            //...
        }

        ValidateUtils.check(user).and("2017-06-05").date("yyyy-MM-dd");




    }


    @Test
    public void regex() {
        ValidateUtils.is("157774105").regex("[1-9]([0-9]{5,11})").and("1").regex("[1-9]","b");
        ValidateUtils.is("18627817977").phone("o").and("张刚").chinese().and("zhanggang").english().english("e")
                .and("gangzhang@fetech.com").email("e").email().and("157774105").regex("[1-9]([0-9]{5,11})").and("1").regex("[1-9]");
    }

    @Test
    public void date() {
        ValidateUtils.is("2017-06-05 14:30:10").date("yyyy-mm-dd HH:mm:ss","a")
                .and("2017-06-05").date("yyyy-MM-dd")
                .and("2017年09月08号").date("yyyy年MM月08号");







        ValidateUtils.is("a").notNull();

        ValidateUtils.is("test").maxLength(20).minLength(4);

        ValidateUtils.is(50).min(20).max(60);


        ValidateUtils.is("a").notNull().and("test").maxLength(20).minLength(4)
                .and(50).min(20).max(60);

        ValidateUtils.is("a").notNull().and("test").maxLength(20,"maxL").minLength(4,"minL")
                .and(50).min(20).max(60);

        ValidateUtils.is("test").maxLength(20,"最大长度不能超过20个字").minLength(4,"最小长度不能少于4个字");

    }

    @Test
    public void i() {
        ValidateUtils.is("192.168.1.123").ip("ip")
                .and("440304199010119094").idCard("id")
                .and("1.1.0.7").ip()
                .and("21010219751115209X").idCard("id");
    }
}
