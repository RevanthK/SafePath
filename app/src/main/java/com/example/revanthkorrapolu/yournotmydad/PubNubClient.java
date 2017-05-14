package com.example.revanthkorrapolu.yournotmydad;

import android.content.Context;
import android.graphics.Color;
import android.os.Debug;
import android.util.Log;
import android.widget.TextView;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNHistoryItemResult;
import com.pubnub.api.models.consumer.history.PNHistoryResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by hemanth on 5/13/17.
 */

public class PubNubClient {
    private static final String TAG="PubNumClient";
    private static final String publishKey="pub-c-c91bbc72-8e34-46b6-a42b-562d79779901";
    private static final String subscribeKey="sub-c-e9b2c83e-383b-11e7-9843-0619f8945a4f";
    private static PubNub mPubNub;
    public static ArrayList<TextView> alerts;


    public static PubNub getPubNub(){
        if(mPubNub==null){
            PNConfiguration pnConfiguration = new PNConfiguration();
            pnConfiguration.setSubscribeKey(subscribeKey);
            pnConfiguration.setPublishKey(publishKey);
            pnConfiguration.setSecure(false);

            mPubNub = new PubNub(pnConfiguration);
        }
        return mPubNub;
    }
    public static void subscribe(final Context context){
        if(mPubNub==null){
            PNConfiguration pnConfiguration = new PNConfiguration();
            pnConfiguration.setSubscribeKey(subscribeKey);
            pnConfiguration.setPublishKey(publishKey);
            pnConfiguration.setSecure(false);

            mPubNub = new PubNub(pnConfiguration);
        }
        mPubNub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {
                if (status.getCategory() == PNStatusCategory.PNConnectedCategory){
                   publish("Whaddup!");
                }
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                    Log.d(TAG,message.getMessage().toString());
                    if(alerts==null){
                        alerts=new ArrayList<TextView>();
                    }
                    if(alerts.size()>=5){
                        alerts.remove(alerts.get(0));
                    }
                    TextView textView= new TextView(context);
                    textView.setText(message.getMessage().getAsString());
                    textView.setTextSize(15);
                    textView.setTextColor(Color.WHITE);
                    alerts.add(textView);
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        });
        mPubNub.history().channel("my_channel").count(5).async(new PNCallback<PNHistoryResult>() {
            @Override
            public void onResponse(PNHistoryResult result, PNStatus status) {
                if(alerts==null){
                    alerts=new ArrayList<TextView>();
                }
                for (PNHistoryItemResult e:result.getMessages()){

                    TextView textView= new TextView(context);
                    textView.setText(e.getEntry().getAsString());
                    textView.setTextSize(15);
                    textView.setTextColor(Color.WHITE);
                    alerts.add(textView);
                }

            }
        });
        mPubNub.subscribe()
                .channels(Arrays.asList("my_channel","clicksend-text")) // subscribe to channels
                .execute();
    }

    public static void publish(String message){
        mPubNub.publish()
                .message(Arrays.asList(message))
                .channel("my_channel")
                .shouldStore(true)
                .usePOST(true)
                .async(new PNCallback<PNPublishResult>() {
                    @Override
                    public void onResponse(PNPublishResult result, PNStatus status) {
                        if (status.isError()) {
                            // something bad happened.
                            Log.d(TAG,"Error When Publishing");
                        } else {
                            Log.d(TAG,"publish worked! timetoken: " + result.getTimetoken());
                        }
                    }
                });
    }

    public static void text(String number,String message){
        mPubNub.publish()
                .message(message)
                .channel("clicksend-text")
                .shouldStore(true)
                .usePOST(true)
                .async(new PNCallback<PNPublishResult>() {
                    @Override
                    public void onResponse(PNPublishResult result, PNStatus status) {

                    }
                });
    }





}
