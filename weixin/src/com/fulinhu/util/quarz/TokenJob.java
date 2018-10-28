package com.fulinhu.util.quarz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fulinhu.po.Menu;
import com.fulinhu.po.Token;
import com.fulinhu.util.CommonUtil;
import com.fulinhu.util.MeanUtil;

public class TokenJob implements Job {
	public static Token token;
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		JobDataMap mergedJobDataMap = arg0.getMergedJobDataMap();
		String appid = mergedJobDataMap.getString("appid");
		String appsecret = mergedJobDataMap.getString("appsecret");
		token = CommonUtil.getToken(appid, appsecret);
		if(token!=null){
			String accessToken=token.getAccessToken();
			if(accessToken!=null&&accessToken!=""&&!"".equals(accessToken)){
				MeanUtil.createMenu(Menu.getMenu(), accessToken);
			}else{
				throw new RuntimeException("获取accessToken错误,重新启动系统。");
			}
		}else{
			throw new RuntimeException("获取accessToken错误,重新启动系统。");
		}
	}
     
}
