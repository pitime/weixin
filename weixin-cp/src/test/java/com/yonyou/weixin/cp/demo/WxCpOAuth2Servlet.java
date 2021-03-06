package com.yonyou.weixin.cp.demo;

import com.yonyou.weixin.common.exception.WxErrorException;
import com.yonyou.weixin.cp.api.WxCpService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class WxCpOAuth2Servlet extends HttpServlet {

  protected WxCpService wxCpService;

  public WxCpOAuth2Servlet(WxCpService wxCpService) {
    this.wxCpService = wxCpService;
  }

  @Override protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    String code = request.getParameter("code");
    try {
      response.getWriter().println("<h1>code</h1>");
      response.getWriter().println(code);

      String[] res = wxCpService.oauth2getUserInfo(code);
      response.getWriter().println("<h1>result</h1>");
      response.getWriter().println(Arrays.toString(res));
    } catch (WxErrorException e) {
      e.printStackTrace();
    }

    response.getWriter().flush();
    response.getWriter().close();

  }


}
