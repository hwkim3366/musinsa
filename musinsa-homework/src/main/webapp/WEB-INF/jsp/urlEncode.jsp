<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
    <title>Url Encode</title>
    <script type="text/javascript" src="/static/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">

    function submit(){

		var inputText = $("#url").val().toLowerCase();

		//공백 불가, http://나 https://로 시작
    	if(inputText.indexOf(" ") == -1 && (inputText.indexOf("http://") == 0 || inputText.indexOf("https://") == 0))
        {
    		$.ajax
            (
	            {
	            	type: "get",
	                url: "/submit",
	                data: {inputUrl: $("#url").val()},
	                success: function(data) 
	                {
	                	$("#result").html(
			            					  "<br></br>"
				                    		+ "<br>" + "Shortning URL : " + data.urlEncodeInfo.shortUrl + "</br>"
				                    		+ "<br>" + "Request Count : " + data.urlEncodeInfo.shortReqCount + "</br>"
				                    		+ "<br></br>"
				                    		+ "<br>" + "Original URL : " + data.urlEncodeInfo.orgUrl + "</br>"
				                    		+ "<br>" + "Request Count : " + data.urlEncodeInfo.orgReqCount + "</br>"
				                    		+ "<br></br>"
				                    		+ "<br>" + "Total Request Count : " + data.urlEncodeInfo.totalReqCount + "</br>"
				                    	 );
                   		 
	                    if(data.urlType == "ORG")
	                    {
	                        window.open(data.urlEncodeInfo.orgUrl, "_blank"); //새 창으로 리다이렉트
	                        //location.href=data.urlEncodeInfo.orgUrl;
	                    }
	                }
	            }
            );
    	}
    	else
        {
    		$("#result").html("<br>" + '유효한 포맷이 아닙니다. "http://" 또는 "https://"로 시작하는 공백 없는 URL만 가능합니다' + "</br>");
        }
    }
</script>
</head>
<body>
    <div id="input">
        URL Input : 
       	<input id="url" value="http://www.naver.com"/>
		<input id="submitButton" type="button" onclick="submit();" value="submit"/>
    </div>
    <div id="result"></div>
</body>
</html>
