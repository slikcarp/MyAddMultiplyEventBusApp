package com.mobileappscompany.training.myaddmultiplyeventbusapp;

/**
 * Created by User on 2/6/2017.
 */

public class MyEventBusServiceHandler {

    public static final String EVENT_CODE_PARAM = "EVENT_CODE";

    public static final int DEFAULT_EVENT = 0;
    public static final int ADD_EVENT = 1;
    public static final int MULTIPLY_EVENT = 2;

    public static final int DEFAULT_NUMBER_VALUE = Integer.MAX_VALUE;

    public static final String FIRST_NUMBER_PARAM = "FIRST_NUMBER";
    public static final String SECOND_NUMBER_PARAM = "SECOND_NUMBER";

    private int eventCode;
    private int result;
    private String error;

    public MyEventBusServiceHandler(int eventCode, int result, String error) {
        this.eventCode = eventCode;
        this.result = result;
        this.error = error;
    }

    public int getEventCode() {
        return eventCode;
    }

    public int getResult() { return result; }

    public String getError() { return error; }
}
