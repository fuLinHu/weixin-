package com.fulinhu.controll;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fulinhu.po.Menu;
import com.fulinhu.po.Token;
import com.fulinhu.util.CommonUtil;
import com.fulinhu.util.MeanUtil;
import com.fulinhu.util.quarz.TokenJob;
import com.fulinhu.util.quarz.TokenSchedule;

public class InitServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

     public void init() throws ServletException {
         // 获取web.xml中配置的参数
         String appid = getInitParameter("appid");
         String appsecret = getInitParameter("appsecret");
         log.info("weixin api appid:{}", appid);
         log.info("weixin api appsecret:{}", appsecret);
         // 未配置appid、appsecret时给出提示
         if ("".equals(appid) || "".equals(appsecret)) {
             log.error("appid and appsecret configuration error, please check carefully.");
         } else {
        	 try {
				TokenSchedule.runTokenJob(appid,appsecret);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
         }
    }
}
