package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dao.UrlEncodeDao;
import com.entity.UrlEncodeInfo;
import com.service.UrlEncodeService;
import com.util.UrlEncoder;
import com.vo.UrlEncodeResult;

@Service("UrlEncodeService")
public class UrlEncodeServiceImpl implements UrlEncodeService {

    private UrlEncodeDao urlEncodeDao;

    @Autowired
    public UrlEncodeServiceImpl(UrlEncoder urlEncoder, UrlEncodeDao urlShortDao)
    {
        this.urlEncodeDao = urlShortDao;
    }

    /**
     * URL encoding, save ,get org/encode URL, counting
     * @param url
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UrlEncodeResult process(String url)
    {
    	UrlEncodeResult urlEncodeResult = new UrlEncodeResult();

    	UrlEncodeInfo urlEncodeInfo = new UrlEncodeInfo();
        
        //기 등록 URL인 경우
        if(urlEncodeDao.isExists(url))
        {
        	urlEncodeInfo = urlEncodeDao.findUrl(url);
            
        	if(urlEncodeInfo.getShortUrl().equals(url))
        	{
        		urlEncodeResult.setUrlType("ORG");
        		urlEncodeInfo.setShortReqCount(urlEncodeInfo.getShortReqCount()+1);
        	}
        	else
        	{
        		urlEncodeResult.setUrlType("SHORT");
        		urlEncodeInfo.setOrgReqCount(urlEncodeInfo.getOrgReqCount()+1);
        	}
        	
            urlEncodeInfo.setTotalReqCount(urlEncodeInfo.getTotalReqCount()+1);
            
            urlEncodeResult.setUrlEncodeInfo(urlEncodeInfo);
        }
        //신규 URL인 경우
        else
        {
        	UrlEncodeInfo currentUrlEncodeInfo = new UrlEncodeInfo();
            
        	currentUrlEncodeInfo.setOrgUrl(url);
        	currentUrlEncodeInfo.setTotalReqCount(1);
        	currentUrlEncodeInfo.setOrgReqCount(1);
        	currentUrlEncodeInfo.setShortReqCount(0);

            urlEncodeInfo =  urlEncodeDao.save(currentUrlEncodeInfo);
            
            String encodeUrl = "";
            
            try
            {
            	encodeUrl = UrlEncoder.encoding(urlEncodeInfo.getSeq());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally 
            {
            	urlEncodeInfo.setShortUrl(encodeUrl);
            }
            
            urlEncodeResult.setUrlEncodeInfo(urlEncodeInfo);
            
            urlEncodeResult.setUrlType("SHORT");
        }
        
        return urlEncodeResult;
    }
}
