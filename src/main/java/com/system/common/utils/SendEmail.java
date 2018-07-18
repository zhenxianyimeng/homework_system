package com.system.common.utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zjb
 * @date 2018/7/18.
 */
public class SendEmail {

    public static final String address = "http://chenkunfinance.com/api/applyInfo/";

    public static final String TITLE = "教师发布新作业";

    public static final String CONTEXT = "教师已经发布新作业，你有新的作业，请在规定时间内完成作业";


    public static final String CONTEXTCHANGEPWD = "修改密码";

    public static final String HOST = "smtp.163.com";
    public static final String PROTOCOL = "smtp";
    public static final int PORT = 25;
    public static final String FROM = "checkVerify@163.com";//发件人的email
    public static final String PWD = "1qaz2wsx";//发件人密码
    public static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    /**
     * 获取Session
     * @return
     */
    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);//设置服务器地址
        props.put("mail.store.protocol" , PROTOCOL);//设置协议
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.port", PORT);//设置端口
        props.put("mail.smtp.auth" , true);

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }

        };
        Session session = Session.getDefaultInstance(props , authenticator);

        return session;
    }

    public static void send(String toEmail , String content, String subject) throws Exception{
        Session session = getSession();
        try {
            System.out.println("--send--"+content);
            // Instantiate a message
            Message msg = new MimeMessage(session);
//            content = "尊敬的用户您好，您此次的验证码为"+content+"请及时验证";
            //Set message attributes
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(content , "text/html;charset=utf-8");

            //Send the message
            Transport.send(msg);
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
            throw new Exception();
        }
    }

    public static String getRandNum() {
        int[] arr = {0,1,2,3,4,5,6,7,8,9};
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<6; i++){
            int num = (int)(Math.random()*10);
            sb.append(arr[num]);
        }
        return sb.toString();
    }

    public static boolean isEmailMatch(String email){
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static void main(String[] args) throws Exception {
        send("871069688@qq.com", getRandNum()+CONTEXT ,TITLE);
    }
}
