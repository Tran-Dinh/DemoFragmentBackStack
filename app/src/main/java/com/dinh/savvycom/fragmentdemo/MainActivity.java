package com.dinh.savvycom.fragmentdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Stack;


/*Demo quan ly stack trong fragment*/

public class MainActivity extends AppCompatActivity implements ScreenFragment1.MyListFragmentListener {

    private FragmentManager manager;
    private ScreenFragment1 screenFragment1;
    private ScreenFragment2 screenFragment2;
    private Stack<Fragment> fragmentStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentStack = new Stack<Fragment>();

        screenFragment1 = new ScreenFragment1();
        screenFragment1.registerForListener(this);

        manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.activity_main, screenFragment1);
        fragmentStack.push(screenFragment1);
        ft.commit();
    }

    @Override
    public void onItemClickedListener(String valueClicked) {

        Toast.makeText(this, valueClicked, Toast.LENGTH_LONG).show();

        FragmentTransaction ft = manager.beginTransaction();

        screenFragment2 = new ScreenFragment2();
        ft.add(R.id.activity_main, screenFragment2);
        fragmentStack.lastElement().onPause();
        ft.hide(fragmentStack.lastElement());
        fragmentStack.push(screenFragment2);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        if (fragmentStack.size() == 2) {
            FragmentTransaction ft = manager.beginTransaction();
            fragmentStack.lastElement().onPause();
            ft.remove(fragmentStack.pop());
            fragmentStack.lastElement().onResume();
            ft.show(fragmentStack.lastElement());
            ft.commit();
        } else {
            super.onBackPressed();
        }
    }
}
