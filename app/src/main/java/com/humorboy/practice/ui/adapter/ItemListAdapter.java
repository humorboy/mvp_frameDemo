package com.humorboy.practice.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.humorboy.practice.R;
import com.humorboy.practice.ui.activity.ThreadPoolTestActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @date    创建时间: 2017/7/12
 * @author  作者: humorboy
 * @describe 描述: CarListAdapter
 * @version  版本 1.0
 */
public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    private ArrayList<HashMap<String,String>> DataList = new ArrayList<>();//列表数据
    private Context mContext;

    public ItemListAdapter(Context context, ArrayList<HashMap<String,String>> Data) {
        this.mContext  =context;
        this.DataList = Data;
    }

    public void setData(ArrayList<HashMap<String,String>> list){
        this.DataList = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //二者区别自行体会
//        View view = View.inflate(parent.getContext(), R.layout.list_item_layout, null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,parent,false);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final HashMap<String,String> data = DataList.get(position);
        if(data!=null){
            if(!TextUtils.isEmpty(data.get("name"))){
                holder.item_tv.setText(""+data.get("name"));
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(data.get("name"));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (DataList != null){
            return DataList.size();
        }else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView item_tv;//名称

        public ViewHolder(View itemView)
        {
            super(itemView);
            item_tv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

    private void switchActivity(String name) {
        if("线程池".equals(name)){
            Intent intent = new Intent(mContext, ThreadPoolTestActivity.class);
            mContext.startActivity(intent);
        }else if("语音识别".equals(name)){

        }else if("LBS".equals(name)){

        }
    }

}
