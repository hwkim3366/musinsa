package com.vo;

import com.entity.UrlEncodeInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * View return VO
 * @author hwkim
 *
 */
@Getter
@Setter
public class UrlEncodeResult {

    private UrlEncodeInfo urlEncodeInfo;
    
    private String urlType;
}

