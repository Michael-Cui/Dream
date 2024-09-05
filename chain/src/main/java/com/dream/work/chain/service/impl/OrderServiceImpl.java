package com.dream.work.chain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dream.work.chain.bean.OrderInfo;
import com.dream.work.chain.db.mapper.OrderMapper;
import com.dream.work.chain.service.OrderService;
import com.dream.work.chain.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper orderMapper;

    private final Map<String, String> fieldParaMap = new HashMap<String, String>(){{
        put("id", "postId");
        put("openid", "openid");
    }};

    @Override
    public void createOrder(OrderInfo orderInfo) {
        orderInfo.setCreateTime(DateUtil.formatDate(new Date()));
        orderMapper.insert(orderInfo);
    }

    @Override
    public List<OrderInfo> getByChainId(String id) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        if(id != null) {
            queryWrapper.eq("postId", id);
        }

        return orderMapper.selectList(queryWrapper);
    }

    @Override
    public List<OrderInfo> getByOpenId(String openId) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        if(openId != null) {
            queryWrapper.eq("openid", openId);
        }

        return orderMapper.selectList(queryWrapper);
    }

    @Override
    public int deleteByMap(Map<String, Object> paras) {
        Map<String, Object> columnMap = new HashMap<>();

        paras.forEach((k, v) -> {
            columnMap.put(fieldParaMap.get(k), v);
        });

        return orderMapper.deleteByMap(columnMap);
    }
}
