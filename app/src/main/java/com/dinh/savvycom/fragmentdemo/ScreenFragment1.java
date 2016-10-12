package com.dinh.savvycom.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by admin on 11/10/2016.
 */

public class ScreenFragment1 extends Fragment{

    View view;
    ListView listView;
    private ListAdapter adapter;
    private MyListFragmentListener listener;

    public interface MyListFragmentListener {
        public void onItemClickedListener(String valueClicked);
    }

    public void registerForListener(MyListFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        view  = inflater.inflate(R.layout.screen1_fragment,container,false);
        initView();
        return view;
    }

    public void initView(){

        listView = (ListView) view.findViewById(R.id.listView1);

        adapter = listView.getAdapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {

                if (listener != null) {
                    listener.onItemClickedListener(adapter.getItem(position).toString());
                } else {
                    throw new IllegalArgumentException("Please Pass Listener");
                }
            }
        });

    }
}
