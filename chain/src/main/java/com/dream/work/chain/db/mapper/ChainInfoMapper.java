package com.dream.work.chain.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dream.work.chain.bean.ChainInfo;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChainInfoMapper extends BaseMapper<ChainInfo> {

    List<ChainInfo> selectChainWithOpenId(@Param("openId") String openId);
}
