package com.mgl.questionnaire.computernetwork.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mgl.questionnaire.computernetwork.util.DateUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@ServerEndpoint("/socket/{platform}")
@Component
public class SocketSever {

    public static final String TemperatureDetection = "TemperatureDetection";
    public static final String AirConditioner = "AirConditioner";

    public static int maxTemperature = 28;
    private static int minTemperature = 15;

    private static Map<String,SocketSever> sessionMap = new HashMap<>();

    private Session session;

    @OnOpen
    public void openSession(Session session, @PathParam("platform") String platform) throws IOException {

        //将session和平台放到map里面
        this.session = session;
        sessionMap.put(platform,this);

        System.out.println("socket通信已经开启....");
        session.getBasicRemote().sendText("建立一个socket连接！");
    }

    @OnClose
    public void closeSession(Session session, @PathParam("platform") String platform){
        sessionMap.remove(platform);
    }

    @OnMessage
    public void onMessage(@PathParam("platform") String platform, String message,Session session) throws IOException{
        RemoteEndpoint.Basic remote = session.getBasicRemote();

        remote.sendText("已收到信息！");

        if (platform.equals(SocketSever.TemperatureDetection)){
            JSONObject jsonObject = JSON.parseObject(message);
            int temperature = jsonObject.getIntValue("temperature");
            Long date = jsonObject.getLongValue("date");

            System.out.println("date:  "+DateUtil.getStringDate(date));
            System.out.println("temperature: " + temperature);

            Map<String,Object> map = new HashMap<>();

            map.put("platform",SocketSever.TemperatureDetection);
            map.put("date",DateUtil.getStringDate(date));
            map.put("temperature",temperature);
            if (temperature > maxTemperature){
                map.put("type",1);
            }else if(temperature<minTemperature){
                map.put("type",2);
            }else {
                map.put("type",0);
            }

            JSONObject jsonObject1 = new JSONObject(map);
            sessionMap.get("server").session.getBasicRemote().sendText(jsonObject1.toString());
        }


    }

    @OnError
    public void sessionError(Session session, Throwable throwable){
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("WebSocket连接发生异常，message:"+throwable.getMessage());
    }
}
