package com.example.administrator.duanxuhui20170503b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import com.duanxuhui.base.MyBase;
import com.duanxuhui.bean.JsonBean;
import com.duanxuhui.util.MyXutils;
import com.duanxuhui.util.UrlUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SearchView sv;
    private AutoCompleteTextView actv;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //设置输入第一个字符就进行提示
        actv.setThreshold(1);
        initData();

    }

    private void initData() {

        MyXutils myXutils = new MyXutils();
        myXutils.getData(UrlUtils.PATH);
        myXutils.send(new MyXutils.Jiekou() {
            @Override
            public void sendData(List<JsonBean.ResultBean.RowsBean> list) {
                MyBase myBase = new MyBase(MainActivity.this, list);
                lv.setAdapter(myBase);
                actv.setAdapter(myBase);
            }
        });
    }

    private void initView() {
//        sv= (SearchView) findViewById(R.id.sv);
        actv= (AutoCompleteTextView) findViewById(R.id.sv);
        lv= (ListView) findViewById(R.id.listView);
    }
}
