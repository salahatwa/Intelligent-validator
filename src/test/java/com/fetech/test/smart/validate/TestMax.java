package com.fetech.test.smart.validate;

import org.junit.Test;

import com.Intelligent.validate.ValidateUtils;

/**
 * Created by ZhangGang on 2017/9/14.
 */
public class TestMax {

    @Test
    public void testMax() {
        ValidateUtils.is("asdasd").minLength(2).maxLength(30);
    }

    @Test
    public void testMax2() {
        ValidateUtils.is("asdasd").maxLength(20).minLength(3);
    }
}
