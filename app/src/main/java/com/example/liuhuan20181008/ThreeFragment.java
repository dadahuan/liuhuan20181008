package com.example.liuhuan20181008;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class ThreeFragment extends Fragment {

    private WaveView wv;
    private ImageView imgcursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_three, container, false);
        wv = v.findViewById(R.id.wv);
        imgcursor = v.findViewById(R.id.img_cursor);
        WaveView.OnWaveChangeListener listener=new WaveView.OnWaveChangeListener() {
            @Override
            public void Onchanged(float y) {
                RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) imgcursor.getLayoutParams();
                layoutParams.setMargins(0,0,0, (int) y);
                imgcursor.setLayoutParams(layoutParams);
            }
        };
        wv.setOnWaveChangeListener(listener);
        return v;
    }
}
