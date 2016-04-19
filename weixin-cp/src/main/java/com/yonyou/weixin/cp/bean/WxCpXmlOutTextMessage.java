package com.yonyou.weixin.cp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.yonyou.weixin.common.api.WxConsts;
import com.yonyou.weixin.common.util.xml.XStreamCDataConverter;

@XStreamAlias("xml")
public class WxCpXmlOutTextMessage extends WxCpXmlOutMessage {
  
  @XStreamAlias("Content")
  @XStreamConverter(value=XStreamCDataConverter.class)
  private String content;

  public WxCpXmlOutTextMessage() {
    this.msgType = WxConsts.XML_MSG_TEXT;
  }
  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  
}
