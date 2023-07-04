package com.zds.springboot.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {

    public static void sendMail(String mailHost,String sendMail,String pop3_smtp ,String address,String subjuect,String content) throws Exception {
        Properties prop = new Properties();
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", mailHost);
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");
        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连接邮件服务器：邮箱类型，帐号，POP3/SMTP协议授权码 163使用：smtp.163.com
        ts.connect(mailHost, sendMail, pop3_smtp);

        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress(sendMail));
        // 指明邮件的收件人，发件人和收件人如果是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
        // 邮件的标题
        message.setSubject(subjuect);
        // 邮件的文本内容
        message.setContent(content, "text/html;charset=UTF-8");

        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

}
