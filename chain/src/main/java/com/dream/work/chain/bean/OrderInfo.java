package com.dream.work.chain.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "chain_order", autoResultMap = true)
public class OrderInfo {
    private String id;
    // 活动ID
    @TableField("postId")
    private String postId;
    // 姓名
    private String name;
    // 备注
    private String memo;
    // 手机号
    private String mobile;
    // 商品数量
    private int quantity;

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
