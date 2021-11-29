package com.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "url_encode_info")
public class UrlEncodeInfo 
{
	//순번(PK)
    @Id
    @SequenceGenerator(name="seq_generator", sequenceName = "seq", initialValue=1000000000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
    @Column(name = "seq")
    private Integer seq;
    
    //원본 URL
    @Column(name="org_url", nullable = false)
    private String orgUrl;
    
    //인코딩 URL
    @Column(name = "short_url")
    private String shortUrl;
    
    //원본 URL 요청 횟수
    @Column(name= "org_req_count")
    private long orgReqCount;
    
    //인코딩 URL 요청 횟수
    @Column(name= "short_req_count")
    private long shortReqCount;
    
    //인코딩 URL + 원본 URL 요청 총 횟수
    @Column(name= "total_req_count")
    private long totalReqCount;
}
