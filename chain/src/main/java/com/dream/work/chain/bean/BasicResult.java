package com.dream.work.chain.bean;

import lombok.Data;

@Data
public class BasicResult<T> {
    private int openid;
    private T data;
}
