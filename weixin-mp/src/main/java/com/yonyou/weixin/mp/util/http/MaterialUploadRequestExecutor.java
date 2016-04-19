package com.yonyou.weixin.mp.util.http;

import com.yonyou.weixin.common.bean.result.WxError;
import com.yonyou.weixin.common.exception.WxErrorException;
import com.yonyou.weixin.common.util.http.RequestExecutor;
import com.yonyou.weixin.common.util.http.Utf8ResponseHandler;
import com.yonyou.weixin.common.util.json.WxGsonBuilder;
import com.yonyou.weixin.mp.bean.WxMpMaterial;
import com.yonyou.weixin.mp.bean.result.WxMpMaterialUploadResult;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.*;
import java.util.Map;

public class MaterialUploadRequestExecutor implements RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> {

  @Override
  public WxMpMaterialUploadResult execute(CloseableHttpClient httpclient, HttpHost httpProxy, String uri, WxMpMaterial material) throws WxErrorException, ClientProtocolException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (httpProxy != null) {
      RequestConfig response = RequestConfig.custom().setProxy(httpProxy).build();
      httpPost.setConfig(response);
    }

    if (material != null) {
      File file = material.getFile();
      if (file == null || !file.exists()) {
        throw new FileNotFoundException();
      }
      MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
      multipartEntityBuilder
          .addBinaryBody("media", file)
          .setMode(HttpMultipartMode.RFC6532);
      Map<String, String> form = material.getForm();
      if (material.getForm() != null) {
        multipartEntityBuilder.addTextBody("description", WxGsonBuilder.create().toJson(form));
      }
      httpPost.setEntity(multipartEntityBuilder.build());
      httpPost.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());
    }

    try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
      WxError error = WxError.fromJson(responseContent);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      } else {
        return WxMpMaterialUploadResult.fromJson(responseContent);
      }
    }finally {
      httpPost.releaseConnection();
    }
  }

}
