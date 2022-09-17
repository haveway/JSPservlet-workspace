package com.kh.model.vo;

import java.sql.Date;

public class Order {
	
	private String userName;//USER_NAME VARCHAR2(20),
    private String phone;//PHONE VARCHAR2(13),
    private String address;//ADDRESS VARCHAR2(100),
    private String message;//MESSAGE VARCHAR2(200),
    private String sandwich;//SANDWICH VARCHAR2(50),
    private String topping;//TOPPING VARCHAR2(100),
    private String cookie;//COOKIE VARCHAR2(50),
    private String payment;//PAYMENT VARCHAR2(12),
    private int price;//PRICE NUMBER,
    private Date orderDate;//ORDER_DATE DATE DEFAULT SYSDATE
    
	public Order() {
		super();
	}
	public Order(String userName, String phone, String address, String message, String sandwich, String topping,
			String cookie, String payment, int price, Date orderDate) {
		super();
		this.userName = userName;
		this.phone = phone;
		this.address = address;
		this.message = message;
		this.sandwich = sandwich;
		this.topping = topping;
		this.cookie = cookie;
		this.payment = payment;
		this.price = price;
		this.orderDate = orderDate;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSandwich() {
		return sandwich;
	}
	public void setSandwich(String sandwich) {
		this.sandwich = sandwich;
	}
	public String getTopping() {
		return topping;
	}
	public void setTopping(String topping) {
		this.topping = topping;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	@Override
	public String toString() {
		return "Order [userName=" + userName + ", phone=" + phone + ", address=" + address + ", message=" + message
				+ ", sandwich=" + sandwich + ", topping=" + topping + ", cookie=" + cookie + ", payment=" + payment
				+ ", price=" + price + ", orderDate=" + orderDate + "]";
	}
	

}
