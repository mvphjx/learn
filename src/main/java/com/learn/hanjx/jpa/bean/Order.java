package com.learn.hanjx.jpa.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	private String id;
	private Float amount = 0f;
	private Set<OrderItem> items = new HashSet<OrderItem>();
	
	@Id @Column(length=12)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(nullable=false)
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	//@OneToMany(cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="order")
	public Set<OrderItem> getItems() {
		return items;
	}
	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
	
	public void addOrderItem(OrderItem orderItem){
		orderItem.setOrder(this);
		this.items.add(orderItem);
	}
	
}
