package com.example.revanthkorrapolu.yournotmydad;

import android.os.Debug;
import android.util.Log;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.util.Arrays;

/**
 * Created by hemanth on 5/13/17.
 */

public class PubNubClient {
    private static final String TAG="PubNumClient";
    private static final String publishKey="pub-c-c91bbc72-8e34-46b6-a42b-562d79779901";
    private static final String subscribeKey="sub-c-e9b2c83e-383b-11e7-9843-0619f8945a4f";
    private static PubNub mPubNub;

    public static void subscribe(){
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
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        });
        mPubNub.subscribe()
                .channels(Arrays.asList("my_channel")) // subscribe to channels
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



}
