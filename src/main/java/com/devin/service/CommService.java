package com.devin.service;

import com.devin.data.Order;

public interface CommService {
    String getCoupons(String name, Order order);
}
