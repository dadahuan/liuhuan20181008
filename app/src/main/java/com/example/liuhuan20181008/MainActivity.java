package com.example.liuhuan20181008;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager pager;
    private TextView first;
    private TextView two;
    private TextView three;
    private List<Fragment> list;
    private FirstFragment firstFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = findViewById(R.id.pager);
        first = findViewById(R.id.first);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        list = new ArrayList<>();
        firstFragment = new FirstFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        list.add(firstFragment);
        list.add(twoFragment);
        list.add(threeFragment);
        first.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        //Fragment进行左右滑动
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.first:
                pager.setCurrentItem(0);
                break;
            case R.id.two:
                pager.setCurrentItem(1);
                break;
            case R.id.three:
                pager.setCurrentItem(2);
                break;
        }
    }
}
