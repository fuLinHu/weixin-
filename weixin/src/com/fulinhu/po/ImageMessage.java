package com.fulinhu.po;
/**
 * 按照微信的接入文档编写的微信文本消息实体
 */
public class ImageMessage extends BaseMessage{

	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
    private String MsgId;
    private String PicUrl;
	private String MediaId;
    public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
    
    public ImageMessage() {
        
    }
    public String getMsgId() {
        return MsgId;
    }
    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}

