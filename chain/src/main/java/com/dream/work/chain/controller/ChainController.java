package com.dream.work.chain.controller;

import com.dream.work.chain.bean.BasicMsg;
import com.dream.work.chain.bean.BasicResult;
import com.dream.work.chain.bean.ChainInfo;
import com.dream.work.chain.bean.OrderInfo;
import com.dream.work.chain.service.ChainService;
import com.dream.work.chain.service.OrderService;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/dreamwork/api/chain")
public class ChainController {

    @Resource
    ChainService chainService;

    @Resource
    OrderService orderService;

    @GetMapping("chainInfo")
    public BasicMsg<List<ChainInfo>> queryChain(@RequestParam(value = "id", required = false) String id,
                                                @RequestParam(value = "openid", required = false) String openId) {
        BasicMsg<List<ChainInfo>> msg = new BasicMsg<>();
        msg.setErrno(0);
        msg.setErrmsg("");

        BasicResult<List<ChainInfo>> result = new BasicResult<>();

        List<ChainInfo> chainInfos = new ArrayList<>();
        if(null != id) {
            chainInfos.add(chainService.getById(id));
        } else if(openId != null) {
            chainInfos = chainService.getByOpenId(openId);
        }

        result.setData(chainInfos);

        msg.setResult(result);
        return msg;
    }

    @PostMapping("chainInfo")
    public BasicMsg<ChainInfo> createChain(@RequestBody ChainInfo chainInfo) {
        log.info("Creating chain, params:{}", chainInfo);

        chainService.createChain(chainInfo);

        BasicMsg<ChainInfo> msg = new BasicMsg<>();
        msg.setErrno(0);
        msg.setErrmsg("");

        BasicResult<ChainInfo> result = new BasicResult<>();
        result.setData(chainInfo);
        msg.setResult(result);

        return msg;
    }

    @PatchMapping("chainInfo")
    public BasicMsg<String> updateChain(@RequestParam(value = "id") String id,
                                        @RequestParam(value = "status", required = false) int status) {
        log.info("Updating chain, id:{}, status:{}", id, status);

        BasicMsg<String> msg = new BasicMsg<>();
        msg.setErrno(0);
        msg.setErrmsg("");

       ChainInfo chainInfo = new ChainInfo();
       chainInfo.setId(id);
       chainInfo.setStatus(status);

        int count = chainService.updateById(chainInfo);

        BasicResult<String> result = new BasicResult<>();
        result.setData("records deleted: " + count);

        msg.setResult(result);

        return msg;
    }

    @GetMapping("order")
    public BasicMsg<List<OrderInfo>> queryOrder(@RequestParam(value = "postId", required = false) String postId,
                                                @RequestParam(value = "openid", required = false) String openId) {
        BasicMsg<List<OrderInfo>> msg = new BasicMsg<>();
        msg.setErrno(0);
        msg.setErrmsg("");

        BasicResult<List<OrderInfo>> result = new BasicResult<>();

        if(null != postId) {
            result.setData(orderService.getByChainId(postId));
        } else {
            result.setData(orderService.getByOpenId(openId));
        }


        msg.setResult(result);
        return msg;
    }

    @PostMapping("order")
    public BasicMsg<OrderInfo> createOrder(@RequestBody OrderInfo orderInfo) {
        log.info("Creating order, params:{}", orderInfo);

        orderService.createOrder(orderInfo);

        BasicMsg<OrderInfo> msg = new BasicMsg<>();
        msg.setErrno(0);
        msg.setErrmsg("");

        BasicResult<OrderInfo> result = new BasicResult<>();
        result.setData(orderInfo);
        msg.setResult(result);

        return msg;
    }

    @DeleteMapping("order")
    public BasicMsg<String> deleteOrder(@RequestParam(value = "id", required = false) String id,
                                       @RequestParam(value = "openid", required = false) String openId) {
        log.info("Deleting order, id:{}, openId:{}", id, openId);

        BasicMsg<String> msg = new BasicMsg<>();
        msg.setErrno(0);
        msg.setErrmsg("");

        Map<String, Object> paras = new HashMap<>();
        if(id != null) {
            paras.put("id", id);
        }
        if(openId != null) {
            paras.put("openid", openId);
        }

        int count = orderService.deleteByMap(paras);

        BasicResult<String> result = new BasicResult<>();
        result.setData("records deleted: " + count);

        msg.setResult(result);

        return msg;
    }
}
