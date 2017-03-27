package com.mobileappscompany.training.myaddmultiplyeventbusapp;

import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText1;
    private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
    }

    public void addValues(View view) {
        Intent i = new Intent(this, MyEventBusIntentService.class);
        int value1 = Integer.valueOf(editText1.getText().toString());
        int value2 = Integer.valueOf(editText2.getText().toString());
        i.putExtra(MyEventBusServiceHandler.FIRST_NUMBER_PARAM,value1);
        i.putExtra(MyEventBusServiceHandler.SECOND_NUMBER_PARAM,value2);
        i.putExtra(MyEventBusServiceHandler.EVENT_CODE_PARAM,MyEventBusServiceHandler.ADD_EVENT);
        startService(i);
    }

    public void multiplyValues(View view) {
        Intent i = new Intent(this, MyEventBusIntentService.class);
        int value1 = Integer.valueOf(editText1.getText().toString());
        int value2 = Integer.valueOf(editText2.getText().toString());
        i.putExtra(MyEventBusServiceHandler.FIRST_NUMBER_PARAM,value1);
        i.putExtra(MyEventBusServiceHandler.SECOND_NUMBER_PARAM,value2);
        i.putExtra(MyEventBusServiceHandler.EVENT_CODE_PARAM,MyEventBusServiceHandler.MULTIPLY_EVENT);
        startService(i);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void displayResult(MyEventBusServiceHandler result) {

        if(result.getError() != null && !result.getError().isEmpty())
        {
            showToastMessage(result.getError());
        } else {
            textView.setText(String.valueOf(result.getResult()));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void showToastMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
