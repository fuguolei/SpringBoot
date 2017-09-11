package com.igalaxy.boot.controller.base;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by fuguolei on 2017/7/14.
 */
@Controller
@RequestMapping("kaptcha")
public class KaptchaController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private Producer captchaProducer;
//
//    @RequestMapping("image")
//    public void image(HttpServletResponse response) throws IOException {
//        Subject subject = SecurityUtils.getSubject();
//        Session session = subject.getSession();
//
//        response.setDateHeader("Expires", 0);
//
//        // Set standard HTTP/1.1 no-cache headers.
//        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//
//        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
//        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//
//        // Set standard HTTP/1.0 no-cache header.
//        response.setHeader("Pragma", "no-cache");
//
//        // return a jpeg
//        response.setContentType("image/jpeg");
//
//        // create the text for the image
//        String capText = captchaProducer.createText();
//
//        // store the text in the session
//        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
//        logger.debug("capText1:{}", capText);
//        // create the image with the text
//        BufferedImage bi = captchaProducer.createImage(capText);
//        ServletOutputStream out = response.getOutputStream();
//
//        // write the data out
//        ImageIO.write(bi, "jpg", out);
//        try {
//            out.flush();
//        } finally {
//            out.close();
//        }
//    }
}
