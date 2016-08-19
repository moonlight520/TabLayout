package com.tablayout.fragment;


import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tablayout.R;

import static com.tablayout.R.layout.pop_layout;

/**
 * A simple {@link Fragment} subclass.
 */
public class TinyTaoFragment extends Fragment implements View.OnClickListener {
    private Button mBtn_show, mBtn_buy, mBtn_dismiss;
    private TextView title;
    private View tiny_tao_layout;
    public PopupWindow popupWindow;
    GestureDetector mGestureDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tiny_tao, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }


    public void initView(View view) {
        mBtn_show = (Button) view.findViewById(R.id.show);
        mBtn_buy = (Button) view.findViewById(R.id.buy);
        mBtn_dismiss = (Button) view.findViewById(R.id.dismiss);
        title = (TextView) view.findViewById(R.id.tiny_tao_title);
        tiny_tao_layout = view.findViewById(R.id.tiny_tao_layout);
        mBtn_show.setOnClickListener(this);
        mBtn_buy.setOnClickListener(this);
        mBtn_dismiss.setOnClickListener(this);
        tiny_tao_layout.setOnTouchListener(new View.OnTouchListener() {
            boolean consume = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (popupWindow.isShowing()) {
                    consume = true;
                    popupWindow.dismiss();
                }
                //Toast.makeText(TinyTaoFragment.this.getActivity(),"layout"+popupWindow.isShowing(),Toast.LENGTH_SHORT).show();

                return consume;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show:
                if (popupWindow == null) {
                    initPopupWindow();
                }
                tiny_tao_layout.setFocusable(true);
                popupWindow.showAsDropDown(title);
                tiny_tao_layout.setAlpha(0.2f);
                break;
            case R.id.buy:
               closePopupwindow();
                break;
            case R.id.dismiss:
                closePopupwindow();
                break;
        }
    }

    public void initPopupWindow() {
        View view = getActivity().getLayoutInflater().inflate(pop_layout, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, 300, false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tiny_tao_layout.setAlpha(1f);
            }
        });
    }
      public void closePopupwindow(){
          if(popupWindow!=null&&popupWindow.isShowing()){
              popupWindow.dismiss();
          }
      }

}
