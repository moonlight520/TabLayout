package com.tablayout.fragment;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tablayout.R;
import com.tablayout.view.ImageViewAnimation;

/**
 * 主页
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment() {
    }

    private ImageViewAnimation imageViewAnimation;
    private ObjectAnimator objectAnimator, objectAnimator1, objectAnimator2, objectAnimator3;
    private AnimatorSet set;
    private Button btn, btn2,btn3,btn4,btn5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageViewAnimation = (ImageViewAnimation) view.findViewById(R.id.imageviewnaimation);
        btn = (Button) view.findViewById(R.id.button);
        btn2 = (Button) view.findViewById(R.id.button2);
        btn3 = (Button) view.findViewById(R.id.button3);
        btn4 = (Button) view.findViewById(R.id.button4);
        btn5 = (Button) view.findViewById(R.id.button5);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        imageViewAnimation.setSweepAngle(360);
        imageViewAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewAnimation.startCustomAnimation();
            }
        });
    }

    @Override
    public void onClick(View v) {
        objectAnimator = ObjectAnimator.ofFloat(imageViewAnimation, "scaleX", 1f, 0.5f,1f);
        objectAnimator1 = ObjectAnimator.ofFloat(imageViewAnimation, "translationY", 0f, 200, 0f);
        objectAnimator2 = ObjectAnimator.ofFloat(imageViewAnimation, "rotationY", 0f, 200, 0f);
        objectAnimator3 = ObjectAnimator.ofFloat(imageViewAnimation, "alpha", 1f, 0f,1);
        switch (v.getId()) {
            //属性值"scale",rotation,"translation","alpha"
            case R.id.button://scale
                objectAnimator.setDuration(3000);
                objectAnimator.setRepeatCount(3);
                objectAnimator.start();
                break;
            case R.id.button2://translation
                objectAnimator1.setRepeatCount(3);
                objectAnimator1.setDuration(3000);
                objectAnimator1.start();
                break;
            case R.id.button3://rotation
                objectAnimator2.setRepeatCount(3);
                objectAnimator2.setDuration(5000);
                objectAnimator2.start();
                break;
            case R.id.button4://alpha
                objectAnimator3.setRepeatCount(3);
                objectAnimator3.setDuration(3000);
                objectAnimator3.start();
                break;
            case R.id.button5:
                set = new AnimatorSet();
                set.play(objectAnimator).with(objectAnimator1).after(objectAnimator2).before(objectAnimator3);
                set.setDuration(5000);
                //TimeInterpolator timeInterpolator=
                set.start();

                break;
        }
    }

}
