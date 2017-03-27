package com.mobileappscompany.training.myaddmultiplyeventbusapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import org.greenrobot.eventbus.EventBus;

public class MyEventBusIntentService extends IntentService {

    public MyEventBusIntentService() {
        super("MyEventBusIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            int result = MyEventBusServiceHandler.DEFAULT_NUMBER_VALUE;
            String error = null;
            try {
                int eventCode = intent.getIntExtra(MyEventBusServiceHandler.EVENT_CODE_PARAM, MyEventBusServiceHandler.DEFAULT_EVENT);

                int firstNumber = intent.getIntExtra(MyEventBusServiceHandler.FIRST_NUMBER_PARAM, MyEventBusServiceHandler.DEFAULT_NUMBER_VALUE);
                int secondNumber = intent.getIntExtra(MyEventBusServiceHandler.SECOND_NUMBER_PARAM,  MyEventBusServiceHandler.DEFAULT_NUMBER_VALUE);


                switch (eventCode) {
                    case MyEventBusServiceHandler.ADD_EVENT:
                        result = firstNumber + secondNumber;
                        break;
                    case MyEventBusServiceHandler.MULTIPLY_EVENT:
                        result = firstNumber * secondNumber;
                        break;
                    default:
                        error = "The selected event code is wrong.";
                        result = MyEventBusServiceHandler.DEFAULT_NUMBER_VALUE;
                        break;
                }
                EventBus.getDefault().post(new MyEventBusServiceHandler(eventCode, result, error));

            } catch (Exception e) {
                e.printStackTrace();
                EventBus.getDefault().post(new MyEventBusServiceHandler(MyEventBusServiceHandler.DEFAULT_EVENT, result, "There was a problem in the process."));
            }
        }
    }
}
