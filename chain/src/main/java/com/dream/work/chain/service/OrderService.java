package com.dream.work.chain.service;

import com.dream.work.chain.bean.OrderInfo;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void createOrder(OrderInfo orderInfo);

    List<OrderInfo> getByChainId(String id);

    List<OrderInfo> getByOpenId(String openId);

    int deleteByMap(Map<String, Object> columnMap);
}
