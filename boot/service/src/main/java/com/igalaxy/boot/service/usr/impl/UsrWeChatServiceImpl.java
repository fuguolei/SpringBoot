package com.igalaxy.boot.service.usr.impl;

import com.google.gson.Gson;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.usr.UsrUser;
import com.igalaxy.boot.domain.usr.UsrWechat;
import com.igalaxy.boot.mapper.BaseMapper;
import com.igalaxy.boot.mapper.usr.UsrWechatMapper;
import com.igalaxy.boot.service.base.BaseServiceImpl;
import com.igalaxy.boot.service.usr.UsrUserService;
import com.igalaxy.boot.service.usr.UsrWechatService;
import com.igalaxy.boot.util.HttpClientUtils;
import com.igalaxy.boot.util.SessionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fuguolei
 */
@Service
public class UsrWeChatServiceImpl extends BaseServiceImpl<UsrWechat> implements UsrWechatService {
    static Gson gson = new Gson();

    @Autowired
    UsrWechatMapper usrWechatMapper;

    @Autowired
    UsrUserService usrUserService;

    @Override
    public BaseMapper<UsrWechat> getMapper() {
        return usrWechatMapper;
    }

    public UsrWechat queryByOpenId(String openId) {
        return null;
    }


    @Override
    public UsrWechat queryByUserId(Long userId) {
        return usrWechatMapper.queryByUserId(userId);
    }

    public BaseResult oauth(String code) {
        WeiXinTockenResponse tocken = getAccessToken(code);
        UsrUser usrUser = usrUserService.queryByOpenId(tocken.openid);
        if (usrUser == null) {
            WeiXinUserInfoResponse userInfo = getUserInfo(tocken);
            usrUser = new UsrUser();
            usrUser.setName(userInfo.nickname);
            usrUser.setHead(userInfo.headimgurl);
            usrUser.setAccount(userInfo.openid);
            usrUser.setPassword("12341fdsfqeur1234");
            BaseResult result = usrUserService.save(usrUser);
            if (!result.isSuccess())
                return result;
            usrUser = (UsrUser) result.getData();
            UsrWechat usrWechat = new UsrWechat();
            usrWechat.setUserId(usrUser.getId());
            usrWechat.setOpenId(userInfo.openid);
            usrWechat.setUserInfo(userInfo.response);
            result = save(usrWechat);
            if (!result.isSuccess())
                return result;
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(usrUser.getAccount(), usrUser.getPassword());
        try {
            subject.login(token);//验证角色和权限
            SessionUtils.setUserId(usrUser.getId());
        } catch (Exception e) {
            return BaseResult.badRequest("认证失败");
        }
        return BaseResult.ok();
    }

    private WeiXinTockenResponse getAccessToken(String code) {
        String accessTockenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appid}&secret={secret}&code={code}&grant_type=authorization_code";
        String atResponse = HttpClientUtils.doGet(accessTockenUrl, "wx855e27a3d08ed53f", "5adfa8174ef10fea49ef1e831e2ffa9e", code);
        //atResponse = "{'access_token':'GG0NdHoXEY_0P2im3POviwT6bUb9F8q46OCF77v5bVsaOxj3XAGbyBFGuCPgu-ugYX-aNbYS5Ty5eDSp3Fzm6E9zyLEnLEnONFp9D9Y9hGI','expires_in':7200,'refresh_token':'wswo5OdM6mD80ROVYsQWNU7b6vYuddzc250oxLE0XlBsWPHP-EGy5YhVpRnRdKX4eLKyP5NiU0SWp50EecuZDUgITyXbH0HEQfrt_NrBASA','openid':'oBXGawUWjZtPflKuUYkEIJS8QktM','scope':'snsapi_userinfo','unionid':'oWmjHwcSwJ4aUXFD71xzYxsk3uIU'}";
        logger.debug("atResponse:{}", atResponse);
        WeiXinTockenResponse wxr = gson.fromJson(atResponse, WeiXinTockenResponse.class);
        logger.debug("wxr:{}", wxr);
        if (wxr.errcode != null) {
            return null;
        }
        return wxr;
    }

    private WeiXinUserInfoResponse getUserInfo(WeiXinTockenResponse wxt) {
        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token={accenToken}&openid={openId}";
        String userInfoResponse = HttpClientUtils.doGet(userInfoUrl, wxt.access_token, "5adfa8174ef10fea49ef1e831e2ffa9e", wxt.openid);
        //userInfoResponse = "{'openid':'oBXGawUWjZtPflKuUYkEIJS8QktM','nickname':'连京帅','sex':1,'language':'zh_CN','city':'Zhengzhou','province':'Henan','country':'CN','headimgurl':'','privilege':[],'unionid':'oWmjHwcSwJ4aUXFD71xzYxsk3uIU'}";
        WeiXinUserInfoResponse weiXinUserInfoResponse = gson.fromJson(userInfoResponse, WeiXinUserInfoResponse.class);
        if (weiXinUserInfoResponse.errcode != null) {
            return null;
        }
        weiXinUserInfoResponse.response = userInfoResponse;
        return weiXinUserInfoResponse;
    }

    static class WeiXinTockenResponse {
        public Integer errcode;
        public String errmsg;
        public String access_token;
        public Integer expires_in;
        public String refresh_token;
        public String openid;
        public String scope;
        public String unionid;
    }

    static class WeiXinUserInfoResponse {
        public Integer errcode;
        public String errmsg;
        public String openid;
        public String nickname;
        public Integer sex;
        public String headimgurl;
        public String unionid;
        public String response;
    }
}
