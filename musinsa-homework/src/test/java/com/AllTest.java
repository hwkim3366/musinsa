package com;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.entity.UrlEncodeInfo;
import com.repository.UrlEncodeInfoRepository;
import com.util.UrlEncoder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AllTest {
	
	@Autowired
    private UrlEncodeInfoRepository urlEncodeInfoRepository;
    
	@Test
    @DisplayName("URL encoding test")
    public void urlEncodingTest() throws Exception
    {
		/**
		 * given
		 */
		HashMap<String, Integer> map = new HashMap<>();
		
		map.put("http://www.naver.com", 1000000000);
		map.put("https://www.daum.net", 1000000001);

		/**
		 * when
		 */
        String result1 = UrlEncoder.encoding(map.get("http://www.naver.com"));
        String result2 = UrlEncoder.encoding(map.get("https://www.daum.net"));

        /**
		 * then
		 */
        Assert.assertEquals(result1, "http://localhost/Qq3pFB");
        Assert.assertEquals(result2, "http://localhost/Rq3pFB");
    }
	
	@Transactional
	@Test
	@DisplayName("save and find test")
    public void saveAndFindTest(){
		
		/**
		 * given
		 */
        String orgUrl = "http://www.naver.com";
        
        UrlEncodeInfo urlEncodeInfo = new UrlEncodeInfo();
        
        urlEncodeInfo.setOrgUrl(orgUrl);
        urlEncodeInfo.setShortUrl("http://localhost/Rq3pFB");
        urlEncodeInfo.setTotalReqCount(1);
        urlEncodeInfo.setOrgReqCount(1);
        urlEncodeInfo.setShortReqCount(0);
        
        /**
		 * when
		 */
        
        //save
        urlEncodeInfoRepository.save(urlEncodeInfo);
        
        //find
        UrlEncodeInfo findInfoByOrgUrl = urlEncodeInfoRepository.findFirstByOrgUrlOrShortUrlOrderBySeqDesc(orgUrl, orgUrl);
        UrlEncodeInfo findInfoByShortUrl = urlEncodeInfoRepository.findFirstByOrgUrlOrShortUrlOrderBySeqDesc(urlEncodeInfo.getShortUrl(), urlEncodeInfo.getShortUrl());
        
        
        /**
		 * then
		 */
        Assert.assertEquals(urlEncodeInfo.getOrgUrl(), findInfoByOrgUrl.getOrgUrl());
        Assert.assertEquals(urlEncodeInfo.getShortUrl(), findInfoByOrgUrl.getShortUrl());
        Assert.assertEquals(urlEncodeInfo.getTotalReqCount(), findInfoByOrgUrl.getTotalReqCount());
        Assert.assertEquals(urlEncodeInfo.getOrgReqCount(), findInfoByOrgUrl.getOrgReqCount());
        Assert.assertEquals(urlEncodeInfo.getShortReqCount(), findInfoByOrgUrl.getShortReqCount());
        
        Assert.assertEquals(urlEncodeInfo.getOrgUrl(), findInfoByShortUrl.getOrgUrl());
        Assert.assertEquals(urlEncodeInfo.getShortUrl(), findInfoByShortUrl.getShortUrl());
        Assert.assertEquals(urlEncodeInfo.getTotalReqCount(), findInfoByShortUrl.getTotalReqCount());
        Assert.assertEquals(urlEncodeInfo.getOrgReqCount(), findInfoByShortUrl.getOrgReqCount());
        Assert.assertEquals(urlEncodeInfo.getShortReqCount(), findInfoByShortUrl.getShortReqCount());
    }
}
