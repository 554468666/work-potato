package com.gm.wj.util;

import cn.hutool.json.JSONObject;
import com.gm.wj.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 发送图片到 doc.sm.ms 网络仓库
 * 2024年5月23日
 */

@Service
public class SendImageUtils {

    @Value("${wj.image.depot.url:https://smms.app/api/v2}")
    private String urll;
    @Value("${wj.image.depot.token:nfi81Owj5Rt1UVjk8XaXfaLgAyWuARJ8}")
    private String token;
    @Value("${wj.image.loca.url:/atafolder/img}")
    private String imgurl;

    HttpPostUtils httpPostUtils = new HttpPostUtils();
    public Result sendImage(String fileName){
        File file = new File(imgurl+fileName);
        JSONObject json = new JSONObject();
        json.put("Authorization",token);
        json.put("smfile",file);
        httpPostUtils.Post(urll+"upload",json.toString());
        return new Result();
    }
}
