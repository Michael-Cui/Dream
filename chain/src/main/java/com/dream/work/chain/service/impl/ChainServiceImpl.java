package com.dream.work.chain.service.impl;

import com.dream.work.chain.bean.ChainInfo;
import com.dream.work.chain.db.mapper.ChainInfoMapper;
import com.dream.work.chain.service.ChainService;
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
public class ChainServiceImpl implements ChainService {

    @Resource
    ChainInfoMapper chainInfoMapper;

    private final Map<String, String> fieldParaMap = new HashMap<String, String>(){{
        put("id", "id");
        put("openid", "openid");
    }};

    @Override
    public void createChain(ChainInfo chainInfo) {
        chainInfo.setCreateTime(DateUtil.formatDate(new Date()));
        chainInfoMapper.insert(chainInfo);
    }

    @Override
    public ChainInfo getById(String id) {
        return chainInfoMapper.selectById(id);
    }

    @Override
    public List<ChainInfo> getByOpenId(String openId) {
        return chainInfoMapper.selectChainWithOpenId(openId);
    }

    @Override
    public int deleteByMap(Map<String, Object> paras) {
        Map<String, Object> columnMap = new HashMap<>();

        paras.forEach((k, v) -> {
            columnMap.put(fieldParaMap.get(k), v);
        });

        return chainInfoMapper.deleteByMap(columnMap);
    }

    @Override
    public int updateById(ChainInfo chainInfo) {
        return chainInfoMapper.updateById(chainInfo);
    }
}
