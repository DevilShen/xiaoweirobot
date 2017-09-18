package com.example.xiaoweirobot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */

public class MsgAdapter extends BaseAdapter {
    private List<Msg> msgList;
    Context mContext;
    private RelativeLayout relativeLayout;

    public MsgAdapter(List<Msg> msgList, Context mContext) {
        this.msgList = msgList;
        this.mContext = mContext;
    }

    @Override
    public Object getItem(int position) {
        return msgList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return msgList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if(msgList.get(position).getFlag() == Msg.RECEIVED){
            relativeLayout = (RelativeLayout) inflater.inflate(R.layout.leftitem,null);
        }
        if(msgList.get(position).getFlag() == Msg.SEND){
            relativeLayout = (RelativeLayout) inflater.inflate(R.layout.rightitem,null);
        }
        TextView tvText = (TextView)relativeLayout.findViewById(R.id.tv);
        TextView timeText = (TextView)relativeLayout.findViewById(R.id.time);
        tvText.setText(msgList.get(position).getContent());
        timeText.setText(msgList.get(position).getTime());
        return relativeLayout;
    }
}
