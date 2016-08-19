package com.tablayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class OpenDoorAnimationActivity extends AppCompatActivity {
    private float lastX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_door_animation);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_POINTER_DOWN:

                float currX=event.getX();

                Toast.makeText(this,"多点触摸"+currX,Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onTouchEvent(event);
    }
}
