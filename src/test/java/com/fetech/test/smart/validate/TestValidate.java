package com.fetech.test.smart.validate;

import com.Intelligent.validate.ValidateUtils;
import com.fetech.test.smart.validate.model.User;

import org.junit.Test;

/**
 * Created by ZhangGang on 2017/9/8.
 */
public class TestValidate {
	/*
	 * @Test public void test() { User user = new User(); user.setAge(18);
	 * user.setName("test");
	 * ValidateUtils.is(user).notNull().and(user.getAge()).max(20).and(user.getName(
	 * )).notNull(); }
	 * 
	 * @Test public void test2() { User user = new User(); user.setAge(50);
	 * user.setName("test"); try {
	 * ValidateUtils.is(user).notNull("A").and(user.getAge()).max(20).and(user.
	 * getName()).notNull(); } catch (Exception e) {
	 * System.out.println(e.getMessage()); } }
	 * 
	 * @Test public void test3() { User user = new User(); user.setAge(18);
	 * user.setName("test"); ValidateUtils.check(user); }
	 */
	@Test
	public void test4() {
		User user = new User();
		user.setAge(30);
		user.setName("test");
		user.setText("啊大神，阿斯蒂芬！");

		// ValidateUtils.is("sdsdsdsds").chinese();

		ValidateUtils.check(user);

	}
}
