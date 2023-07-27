package com.android.test2mvvm.test6.test_fragment.test11;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.util.Loge;

import org.greenrobot.eventbus.EventBus;

public class MessageReciever extends BroadcastReceiver {

    private static final String SMS_RECEIVER_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder sBuilder = new StringBuilder();
        String format = intent.getStringExtra("format");
        if(SMS_RECEIVER_ACTION.equals(intent.getAction()))
        {
            Bundle bundle = intent.getExtras();
            if(null != bundle)
            {
                Object[] pdus = (Object[])bundle.get("pdus");
                assert pdus != null;
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for(int i = 0; i < messages.length; ++i)
                {
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i],format);
                }
                for(SmsMessage msg : messages)
                {
                    sBuilder.append("来自：").append(msg.getDisplayOriginatingAddress()).append("\n").append("短信内容：");
                    sBuilder.append(msg.getDisplayMessageBody()).append("\n");
                }
            }
        }
        Loge.e("你收到一条信息");
        Loge.e(sBuilder.toString());
        Toast.makeText(context, "吆喝，快看!! 您收到了一条短信!!\n" + sBuilder.toString(), Toast.LENGTH_LONG).show();

        EventBus.getDefault().register(Test2_App.getInstance());
        EventBus.getDefault().postSticky("你收到一条短信:"+sBuilder.toString());
        EventBus.getDefault().unregister(Test2_App.getInstance());

    }

}
