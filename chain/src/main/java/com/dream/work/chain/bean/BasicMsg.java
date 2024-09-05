package com.dream.work.chain.bean;

import lombok.Data;

@Data
public class BasicMsg<T> {

    private int errno;
    private String errmsg;
    private BasicResult<T> result;
}
