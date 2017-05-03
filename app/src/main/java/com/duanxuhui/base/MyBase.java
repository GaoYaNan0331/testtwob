package com.duanxuhui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.duanxuhui.bean.JsonBean;
import com.example.administrator.duanxuhui20170503b.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * data 2017/5/3  19:03.
 * author:段旭晖(Administrator)
 * function:
 */
//Filterable 筛选数据的接口
public class MyBase extends BaseAdapter implements Filterable{
    private Context context;
    private List<JsonBean.ResultBean.RowsBean> mlist;
    private List<JsonBean.ResultBean.RowsBean> list;
    private MyFilter mfilter;
    public MyBase(Context context, List<JsonBean.ResultBean.RowsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.mybase,null);
            holder=new ViewHolder();
            holder.image= (ImageView) convertView.findViewById(R.id.imageView);
            holder.text1= (TextView) convertView.findViewById(R.id.text1);
            holder.text2= (TextView) convertView.findViewById(R.id.text2);
            holder.text3= (TextView) convertView.findViewById(R.id.text3);
            holder.text4= (TextView) convertView.findViewById(R.id.text4);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        //设置在内存中缓存，在SD卡里缓存
        DisplayImageOptions options=new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoader.getInstance().displayImage(list.get(position).getInfo().getDefault_image(),holder.image,options);
        holder.text1.setText(list.get(position).getInfo().getLoupan_name());
        holder.text2.setText(list.get(position).getInfo().getSub_region_title()+"       "+list.get(position).getInfo().getPrice()+"/"+list.get(position).getInfo().getNew_price_back());
        holder.text3.setText(list.get(position).getInfo().getAddress());
        holder.text4.setText(list.get(position).getInfo().getSale_title());
        return convertView;
    }
    class ViewHolder{
        private ImageView image;
        private TextView text1,text2,text3,text4;
    }
    //得到一个自己定义的过滤器
    @Override
    public Filter getFilter() {
        if (mfilter==null){
            mfilter=new MyFilter();
        }
        return mfilter;
    }
    //自定义一个过滤器的类
    class MyFilter extends Filter{
        //得到筛选的结果
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults();
            if (mlist==null){
                mlist=new ArrayList<>(list);
            }
            if (constraint==null || constraint.length()==0){
                results.values=mlist;
                results.count=mlist.size();
            }else{
                List<JsonBean.ResultBean.RowsBean> slist=new ArrayList<>();
                String str=constraint.toString().trim();
                for (JsonBean.ResultBean.RowsBean s:mlist){
                    if (s.getInfo().getLoupan_name().contains(str)){
                        slist.add(s);
                    }
                }
                results.values=slist;
                results.count=slist.size();
            }
            return results;
        }
        //返回删选的结果，设置展示的数据
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list= (List<JsonBean.ResultBean.RowsBean>) results.values;
            if (results.count>0){
                notifyDataSetChanged();
            }else
            notifyDataSetInvalidated();
        }
    }



}
