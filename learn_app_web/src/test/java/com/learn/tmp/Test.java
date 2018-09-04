package com.learn.tmp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	
	private static Logger logger = LoggerFactory.getLogger(Test.class);
	
	public static void main(String[] args) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(new Order("1", "a_1"));
		orderList.add(new Order("2", "2_1"));
		orderList.add(new Order("3", "3_1"));
		orderList.add(new Order("4", "4_1"));
		orderList.add(new Order("5", "5_1"));
		orderList.add(new Order("6", "6_1"));
		
	}
}
