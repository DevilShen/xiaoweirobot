package com.example.xiaoweirobot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;

public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList;
    private ListView lv;
    private EditText send_Text;
    private Button send_btn;
    private String content_str;
    private MsgAdapter msgAdapter;
    private String[] welcome_array;
    private double currentTime=0, oldTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        lv = (ListView)findViewById(R.id.list_item);
        send_Text = (EditText)findViewById(R.id.text_edit);
        send_btn = (Button)findViewById(R.id.send_button);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicked();
            }
        });
        msgList = new ArrayList<>();
        msgAdapter = new MsgAdapter(msgList,this);
        lv.setAdapter(msgAdapter);
        Msg listMsg = new Msg(getRandomWelcomeTips(),Msg.RECEIVED,getTime());
        msgList.add(listMsg);
    }


    private String getRandomWelcomeTips() {
        String welcome_tip = null;
        welcome_array = this.getResources()
                .getStringArray(R.array.welcome_tips);
        int index = (int) (Math.random() * (welcome_array.length - 1));
        welcome_tip = welcome_array[index];
        return welcome_tip;
    }

    private String getTime(){
        currentTime = System.currentTimeMillis();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd-hh:mm:ss");
        String str = ft.format(new Date());
        if(currentTime - oldTime >= 500){
            oldTime = currentTime;
            return str;
        }else {
            return "";
        }
    }

    private void onClicked(){
        getTime();
        content_str = send_Text.getText().toString();
        send_Text.setText("");
        String dropk = content_str.replace(" ","");
        String droph = dropk.replace("\n","");
        Msg msg = new Msg(content_str,Msg.SEND,getTime());
        msgList.add(msg);
        if(msgList.size() > 30){
            for(int i = 0 ; i < msgList.size() ; i++){
                msgList.remove(i);
            }
        }
        msgAdapter.notifyDataSetChanged();
        String url = "http://www.tuling123.com/openapi/api?key=6af9822f5491fadfc142b53818bbd63a&info=";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                final String responseText = response.body().string();
                final Responsee responsee1 = Utility.handleResponse(responseText);
                Log.d("MainActivity", responsee1.getText());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(send_Text.getWindowToken(),0);
                        Msg msg1 = new Msg();
                        msg1.setContent(responsee1.getText());
                        msg1.setTime(getTime());
                        msg1.setFlag(Msg.RECEIVED);
                        msgList.add(msg1);
                        msgAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }
}
