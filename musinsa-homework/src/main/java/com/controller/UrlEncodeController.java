package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.service.UrlEncodeService;
import com.vo.UrlEncodeResult;

@Controller
public class UrlEncodeController {
	
	private UrlEncodeService urlEncodeService;

    @Autowired
    public UrlEncodeController(UrlEncodeService urlEncodeService)
    {
        this.urlEncodeService = urlEncodeService;
    }
    
    /**
     * 페이지 호출
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/")
    public String entering() throws Exception
    {
        return "urlEncode";
    }
    
    /**
     * submit
     * @param inputUrl
     * @return
     */
    @GetMapping(value = "/submit")
    @ResponseBody
    public UrlEncodeResult process(@RequestParam String inputUrl)
    {
        return urlEncodeService.process(inputUrl);
    }
    
}
