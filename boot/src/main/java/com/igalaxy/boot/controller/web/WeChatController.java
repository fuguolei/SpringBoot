package com.igalaxy.boot.controller.web;

import com.google.gson.Gson;
import com.igalaxy.boot.controller.BaseController;
import com.igalaxy.boot.util.HttpClientUtils;
import com.igalaxy.boot.util.WchatUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuguolei on 2017/8/14.
 */
public class WeChatController extends BaseController {
    protected Map<String, Object> getPageParams() {
        HttpServletRequest request = getServletRequest();
        Map<String, Object> pageParams = new HashMap<>();
        if (request.getParameter("rows") != null) {
            pageParams.put("pageSize", Integer.parseInt(request.getParameter("rows")));
            pageParams.put("pageNo", Integer.parseInt(request.getParameter("page")));
        }
        return pageParams;
    }

    protected Map<String, Object> getWchatParams(HttpServletRequest request) {
        Map<String, Object> params;
        try {
            long currentTime = System.currentTimeMillis();
            if (WchatParams.ticketExpireTime < currentTime) {
                if (WchatParams.accessTokenExpireTime < currentTime) {
                    AccessToken accessToken = getAccessToken();
                    WchatParams.accessTokenExpireTime = currentTime + accessToken.expires_in * 1000;
                    WchatParams.accessToken = accessToken.access_token;
                }
                Ticket ticket = getTicket(WchatParams.accessToken);
                WchatParams.ticket = ticket.ticket;
                WchatParams.ticketExpireTime = currentTime + ticket.expires_in * 1000;
            }
            StringBuffer url = request.getRequestURL();
            String query = request.getQueryString();
            if (query != null && query.length() > 0) {
                url.append("?");
                url.append(request.getQueryString());
            }
            params = WchatUtil.sign("wx855e27a3d08ed53f", WchatParams.ticket, url.toString());
        } catch (Exception e) {
            params = new HashMap<>();
        }
        return params;
    }

    static final Gson gson = new Gson();

    private AccessToken getAccessToken() {
        String accessTockenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}";
        String atResponse = HttpClientUtils.doGet(accessTockenUrl, "wx855e27a3d08ed53f", "5adfa8174ef10fea49ef1e831e2ffa9e");
        logger.debug("AccessToken:{}", atResponse);
        AccessToken wxr = gson.fromJson(atResponse, AccessToken.class);
        logger.debug("wxr:{}", wxr);
        if (wxr.errcode != null) {
            return null;
        }
        return wxr;
    }

    static class AccessToken {
        public String errcode;
        public String access_token;
        public Long expires_in;
    }

    private Ticket getTicket(String accessToken) {
        String ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={token}&type=jsapi";
        String atResponse = HttpClientUtils.doGet(ticketUrl, accessToken);
        logger.debug("Ticket:{}", atResponse);
        Ticket wxr = gson.fromJson(atResponse, Ticket.class);
        logger.debug("wxr:{}", wxr);
        if (wxr.errcode != null && wxr.errcode == 0) {
            return wxr;
        }
        return null;
    }

    static class Ticket {
        public Integer errcode;
        public String ticket;
        public Long expires_in;
    }
}