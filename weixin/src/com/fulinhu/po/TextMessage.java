package com.fulinhu.po;
/**
 * 按照微信的接入文档编写的微信文本消息实体
 */
public class TextMessage extends BaseMessage {

   
    private String Content;
    private String MsgId;
   
    public String getContent() {
        return Content;
    }
    public void setContent(String content) {
        Content = content;
    }
    public String getMsgId() {
        return MsgId;
    }
    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}

