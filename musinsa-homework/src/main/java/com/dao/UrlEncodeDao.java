package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.entity.UrlEncodeInfo;
import com.repository.UrlEncodeInfoRepository;

@Repository
public class UrlEncodeDao {

    private final UrlEncodeInfoRepository urlEncodeInfoRepository;

    @Autowired
    public UrlEncodeDao(UrlEncodeInfoRepository urlEncodeInfoRepository)
    {
        this.urlEncodeInfoRepository = urlEncodeInfoRepository;
    }

    /**
     * URL 기 등록 여부 체크
     * @param url
     * @return
     */
    public boolean isExists(String url)
    {
        return urlEncodeInfoRepository.existsByShortUrlOrOrgUrl(url, url);
    }

    /**
     * URL 조회
     * @param url
     * @return
     */
    public UrlEncodeInfo findUrl(String url)
    {
        return urlEncodeInfoRepository.findFirstByOrgUrlOrShortUrlOrderBySeqDesc(url, url);
    }

    /**
     * URL encoding 후 저장
     * @param shortUrl
     * @return
     */
    public UrlEncodeInfo save(UrlEncodeInfo urlEncodeInfo)
    {
        return urlEncodeInfoRepository.save(urlEncodeInfo);
    }

}
