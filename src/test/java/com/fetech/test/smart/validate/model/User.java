package com.fetech.test.smart.validate.model;

import java.util.Date;

import javax.xml.bind.ValidationException;

import com.Intelligent.annotations.*;
import com.Intelligent.annotations.Langauge.LanguageType;
import com.Intelligent.validate.ValidateUtils;

/**
 * 
 * @author salah atwa
 *
 */
public class User {

	@NotNull(msg = "Name can not be empty")
	@MaxLength(value = 20, msg = "Name can not exceed 20 characters")
	private String name;

	private Date birthday;

	@IdCard
	private String idcard;

	@Langauge(type = LanguageType.AR)
	private String text;

	@Max(30)
	@Min(12)
	private int age;

	@Email
	@MaxLength(50)
	private String email;

	@Phone
	private String phone;

	@Regex("[1-9]([0-9]{5,11})")
	private String qq;

	@AcceptedValues(acceptValues = { "F", "M" }, msg = "Invalid Geneder Type please Type : F OR M")
	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return (Date) birthday.clone();
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static void main(String[] args) throws ValidationException {
		User user = new User();
		user.setAge(222);
		user.setName("aaa");
		user.setText("啊大神，阿斯蒂芬！");
		user.setGender("F");

		// ValidateUtils.is("sdsdsdsds").chinese();

		try {
			ValidateUtils.check(user);
		} catch (Exception ex) {
			throw new ValidationException("AAAAA");
		}

	}

}
