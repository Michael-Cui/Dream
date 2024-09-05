package com.dream.work.chain.service;

import com.dream.work.chain.bean.ChainInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChainService {
    void createChain(ChainInfo chainInfo);

    ChainInfo getById(String id);

    List<ChainInfo> getByOpenId(String openId);

    int deleteByMap(Map<String, Object> columnMap);

    int updateById(ChainInfo chainInfo);
}
