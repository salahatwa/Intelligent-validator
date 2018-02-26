package com.fetech.test.smart.validate;

import org.junit.Test;

import com.Intelligent.validate.ValidateUtils;

/**
 * Created by ZhangGang on 2017/9/22.
 */
public class TestChinese {

    @Test
    public void test1() {
        ValidateUtils.is("啊大神，阿斯蒂芬！").chinese();
        try {
            ValidateUtils.is(",asd").chinese("c");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
