package com.epam.ryndych.database.model;

import java.sql.Date;

public class Order {
	private User user;
	private String orderStatus;
	private Date orderDate;
	private String paymentMethod;
	private String obtainingMethod;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if (user != null)
			this.user = user;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		if (orderDate != null)
			this.orderDate = orderDate;
	}

	public String getObtainingMethod() {
		return obtainingMethod;
	}

	public void setObtainingMethod(String obtainingMethod) {
		this.obtainingMethod = obtainingMethod;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
