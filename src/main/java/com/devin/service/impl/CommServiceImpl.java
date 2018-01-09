package com.devin.service.impl;

import com.devin.data.Order;
import com.devin.service.CommService;

public class CommServiceImpl implements CommService {
    @Override
    public String getCoupons(String name, Order order) {
        return name;
    }
}
