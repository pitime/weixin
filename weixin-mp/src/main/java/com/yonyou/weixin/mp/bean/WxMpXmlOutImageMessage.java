package com.yonyou.weixin.mp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.yonyou.weixin.common.api.WxConsts;
import com.yonyou.weixin.common.util.xml.XStreamMediaIdConverter;

@XStreamAlias("xml")
public class WxMpXmlOutImageMessage extends WxMpXmlOutMessage {

  @XStreamAlias("Image")
  @XStreamConverter(value = XStreamMediaIdConverter.class)
  private String mediaId;

  public String getMediaId() {
    return mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

  public WxMpXmlOutImageMessage() {
    this.msgType = WxConsts.XML_MSG_IMAGE;
  }

}
