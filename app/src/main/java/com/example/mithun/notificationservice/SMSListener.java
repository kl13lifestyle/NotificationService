package com.example.mithun.notificationservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class SMSListener extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("Inside SMS Onreceive","Inside");
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String messageBody = smsMessage.getMessageBody();
                Log.v("Inside If condition",messageBody);
                Toast.makeText(context,messageBody,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
