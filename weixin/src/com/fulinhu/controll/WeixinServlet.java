package com.fulinhu.controll;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.fulinhu.po.Article;
import com.fulinhu.po.Image;
import com.fulinhu.po.ImageMessage;
import com.fulinhu.po.NewsMessage;
import com.fulinhu.po.TextMessage;
import com.fulinhu.util.CheckUtil;
import com.fulinhu.util.MessageUtil;
import com.fulinhu.util.quarz.TokenJob;
 
/**
 * 接收微信服务器发送的4个参数并返回echostr
 */
public class WeixinServlet extends HttpServlet {
 
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // 接收微信服务器以Get请求发送的4个参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
         
        PrintWriter out = response.getWriter();
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);        // 校验通过，原样返回echostr参数内容
        }
    }
 
    /**
     * 接收并处理微信客户端发送的请求
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/xml;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            Map<String, String> map = MessageUtil.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String eventType = map.get("Event");
            String message = null;
            long createTime=new Date().getTime();
            if("event".equals(msgType)){
            	if("subscribe".equals(eventType)){
            		TextMessage text = new TextMessage();
            		text.setFromUserName(toUserName);         // 发送和回复是反向的
            		text.setToUserName(fromUserName);
            		text.setMsgType(msgType);
            		text.setCreateTime(createTime);
            		text.setContent("欢迎来到在线编程交流。\n\n"
            				+"1.课程介绍。\n\n"
            				+ "2.咨询方式。\n\n"
            				+ "3.图文消息。\n\n");
            		message = MessageUtil.textMessageToXML(text);
            	}else if("unsubscribe".equals(eventType)){
            		
            	}
            }else if ("text".equals(msgType)) {                // 对文本消息进行处理
            	String content = map.get("Content");
            	TextMessage text = new TextMessage();
            	text.setFromUserName(toUserName);         // 发送和回复是反向的
            	text.setToUserName(fromUserName);
            	text.setMsgType(msgType);
            	text.setCreateTime(createTime);
            	if("1".equals(content)){
            		text.setContent("该课程是介绍java编程的课程。");
            	}else if("2".equals(content)){
            		text.setContent("我们的联系方式是\n\n"
            				+ "电话：12452782456\n\n"
            				+ "邮箱：120@qq.com\n\n"
            				+ "敬请您的加入。");
            	}else if("?".equals(content)||"？".equals(content)){
            		text.setContent("欢迎来到在线编程交流。\n\n"
            				+"1.课程介绍。\n\n"
            				+ "2.咨询方式。\n\n"
            				+ "3.图文消息。\n\n"
            				+ "4.图片信息。\n\n");
            	}else if("3".equals(content)){
            		List<Article> articleList=new ArrayList<Article>();
            		NewsMessage newsMessage=new NewsMessage();
            		newsMessage.setArticles(articleList);
            		newsMessage.setToUserName(fromUserName);
                    newsMessage.setFromUserName(toUserName);
                    newsMessage.setCreateTime(createTime);
                    newsMessage.setMsgType("news");
            		Article article=new Article();
            		articleList.add(article);
		    		article.setTitle("haojiahong的博客");
		            article.setDescription("我不是高手，我不会武功。"); 
		            article.setPicUrl("https://3e1e3378.ngrok.io/weixin/image/marray.jpg");
		            article.setUrl("http://www.cnblogs.com/haojiahong");
		            newsMessage.setArticleCount(articleList.size());
		            message = MessageUtil.textMessageToXML(newsMessage);
		            out.print(message); 
		            System.out.println(message);  
		            return;
            	}else if("4".equals(content)){
            		Image image = new Image();
            		image.setMediaId("IzcmxtHMM94ersqf3Vh3V9woi3A3Y1wlJ6Y8FEkUyHMYCUaN7SHcG3j4cLktXHry");
            		ImageMessage imageMessage = new ImageMessage();
            		imageMessage.setFromUserName(toUserName);
            		imageMessage.setToUserName(fromUserName);
            		imageMessage.setMsgType("image");
            		imageMessage.setCreateTime(createTime);
            		imageMessage.setImage(image);
            		message = MessageUtil.textMessageToXML(imageMessage);
            		System.out.println(message);
            		out.print(message); 
            		return;
            	}else{
            		text.setContent("☎");
            	}
                message = MessageUtil.textMessageToXML(text);
                out.print(message); 
                System.out.println(message);
            }else if("image".equals(msgType)){
            	String picUrl = map.get("PicUrl");
            	String msgId = map.get("MsgId");
            	String mediaId = map.get("MediaId");
            	ImageMessage text = new ImageMessage();
                text.setFromUserName(toUserName);         // 发送和回复是反向的
                text.setToUserName(fromUserName);
                text.setMsgType(msgType);
                text.setCreateTime(createTime);
                text.setPicUrl(picUrl);
                text.setMsgId(msgId);
                text.setMediaId(mediaId);
                message = MessageUtil.textMessageToXML(text);
                System.out.println(message);      
            }
            out.print(message);                            // 将回应发送给微信服务器
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }
 
}