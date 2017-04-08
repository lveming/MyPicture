package com.lm.demo.mypicture.util;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/4/8.
 */

public class PictureUtil {
    public static PictureBean getData(String string){

        if (!string.equals("")){
            Gson gson=new Gson();
            PictureBean pictureBean=gson.fromJson(string,PictureBean.class);
            return pictureBean;
        }
        return null;

    }
}
