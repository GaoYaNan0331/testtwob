package com.duanxuhui.util;

import com.duanxuhui.bean.JsonBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * data 2017/5/3  15:58.
 * author:段旭晖(Administrator)
 * function:
 */

public class MyXutils {

    public void getData(String path){
        //网络请求
        RequestParams params=new RequestParams(UrlUtils.PATH);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                JsonBean jsonBean = gson.fromJson(result, JsonBean.class);
                List<JsonBean.ResultBean.RowsBean> rows = jsonBean.getResult().getRows();
                jiekou.sendData(rows);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
            //没网的时候走这里
            @Override
            public boolean onCache(String result) {
                Gson gson=new Gson();
                JsonBean jsonBean = gson.fromJson(result, JsonBean.class);
                List<JsonBean.ResultBean.RowsBean> rows = jsonBean.getResult().getRows();
                jiekou.sendData(rows);
                return true;
            }
        });
    }

    //接口回调。回传数据集合
    Jiekou jiekou;
    public interface Jiekou{
        public void sendData( List<JsonBean.ResultBean.RowsBean> list);
    }
    public void send(Jiekou jiekou){
        this.jiekou=jiekou;
    }
}
