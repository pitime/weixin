package com.yonyou.weixin.mp.api;

import com.yonyou.weixin.common.bean.WxAccessToken;
import com.yonyou.weixin.common.util.http.ApacheHttpClientBuilder;

import javax.net.ssl.SSLContext;
import java.io.File;

/**
 * 微信客户端配置存储
 * @author chanjarster
 *
 */
public interface WxMpConfigStorage {

  public String getAccessToken();

  public boolean isAccessTokenExpired();

  /**
   * 强制将access token过期掉
   */
  public void expireAccessToken();

  /**
   * 应该是线程安全的
   * @param accessToken
   */
  public void updateAccessToken(WxAccessToken accessToken);

  /**
   * 应该是线程安全的
   * @param accessToken
   * @param expiresIn
   */
  public void updateAccessToken(String accessToken, int expiresIn);

  public String getJsapiTicket();

  public boolean isJsapiTicketExpired();

  /**
   * 强制将jsapi ticket过期掉
   */
  public void expireJsapiTicket();

  /**
   * 应该是线程安全的
   * @param jsapiTicket
   */
  public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds);

  public String getCardApiTicket();

  public boolean isCardApiTicketExpired();

  /**
   * 强制将卡券api ticket过期掉
   */
  public void expireCardApiTicket();

  /**
   * 应该是线程安全的
   * @param cardApiTicket
   */
  public void updateCardApiTicket(String cardApiTicket, int expiresInSeconds);

  public String getAppId();

  public String getSecret();

    public String getPartnerId();

    public String getPartnerKey();

  public String getToken();

  public String getAesKey();

  public long getExpiresTime();

  public String getOauth2redirectUri();

  public String getHttp_proxy_host();

  public int getHttp_proxy_port();

  public String getHttp_proxy_username();

  public String getHttp_proxy_password();
  
  public File getTmpDirFile();

  public SSLContext getSSLContext();

  /**
   * http client builder
   * @return ApacheHttpClientBuilder
   */
  public ApacheHttpClientBuilder getApacheHttpClientBuilder();
}
