package com.dream.work.chain.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ChainInfo {
    //id
    private String id;
    // 标题
    private String title;
    // 内容
    private String content;
    // 图片
    @TableField("fileId")
    private String fileID;
    // 场景
    @TableField("sceneIndex")
    private int sceneIndex; // 0-活动，1-拼团
    // 当场景为1时才有（拼团专用字段）
    private float amount;
    // 当前状态
    // 0 - 正常
    // 1 - 结束
    private int status;
    // 报名上限
    private int maximum;

    // TODO user info same with info in Order, may be refactored
    // 用户信息
    @TableField("nickName")
    private String nickName;
    @TableField("avatarUrl")
    private String avatarUrl;
    private String city;
    private String country;
    private int gender;
    private String language;

    private String openid;
    private String appid;

    @TableField("createTime")
    private String createTime;
}
