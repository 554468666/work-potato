package com.gm.wj;

import com.gm.wj.util.SendImageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WjTest {
    @Autowired
    SendImageUtils sendImageUtils;


    @Test
    public void testpost(){
        /*tt1.fileUpload();*/
        //sendImageUtils.sendImage("320.jpg");
    }
}
