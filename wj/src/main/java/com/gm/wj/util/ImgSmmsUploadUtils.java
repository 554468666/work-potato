package com.gm.wj.util;

import com.alibaba.fastjson.JSONObject;
import com.gm.wj.result.Result;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.UUID;
@Slf4j
@Component
public class ImgSmmsUploadUtils {
    Logger logger = LoggerFactory.getLogger(ImgSmmsUploadUtils.class);
    @Value("${wj.image.depot.ur:https://sm.ms/api/v2/upload}")
    private String reqUrl;

    /**
     * 上传图片到图床
     * @return
     */
    public Result fileUpload(String fileurlname) {
        try {
            File file = null;
            //获取图片文件对象
            file = new File(fileurlname);
            if (!file.exists()){
                return new Result("FAIL","文件上传失败!","");
            }

            //这里是向SM.MS服务器发送上传图片请求
            HttpResponse<String> response1 = Unirest.post(reqUrl)
                    //这里*****是指上一步获取的token
                    .header("Authorization", "nfi81Owj5Rt1UVjk8XaXfaLgAyWuARJ8")
                    .field("smfile", file)
                    .asString();
            JSONObject jsonObject = JSONObject.parseObject(response1.getBody());
            //把本地创建的文件删除，不占用本地资源
            file.delete();
            //imageUrl存放上传图片的地址，方便返回前端
            String imageUrl = null;
            //当上传的图片重复时
            if (jsonObject.getString("code").equals("image_repeated")){
                imageUrl=jsonObject.getString("images");
            }
            else {
                imageUrl= JSONObject.parseObject(jsonObject.getString("data")).getString("url");
            }
            if (imageUrl!=null){
                return new Result("SUCCESS", imageUrl.toString(),"");

            }else return new Result("FAIL", jsonObject.getString("message"),"");
        } catch (Exception e) {
            logger.info(e.getMessage());
            return new Result("FAIL", String.valueOf(e.getMessage()),"");
        }
    }

    public Result imgUrlInsertDatabase(){


        return new Result("","","");
    }
}
