package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.UrlEncodeInfo;

public interface UrlEncodeInfoRepository extends JpaRepository<UrlEncodeInfo, Integer>
{
	UrlEncodeInfo findFirstByOrgUrlOrShortUrlOrderBySeqDesc(String org_url, String short_url);
    
    boolean existsByShortUrlOrOrgUrl(String short_url, String org_url);
}
