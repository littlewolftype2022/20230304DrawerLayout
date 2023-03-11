package com.example.a20230304drawerlayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawLayout;
    private ListView mDrawerList;
    private String [] mItemArray;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mItemArray = getResources().getStringArray(R.array.item_array);

        mDrawerList.setAdapter(new ArrayAdapter<String >(this,R.layout.drawer_list_item,mItemArray));

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    }
    private class DrawerItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    private void selectItem(int position){

        Fragment fragment = new Fragment();
        Bundle args = new Bundle();
        args.putInt(MyFragment.ITEM_NUMBER_STRING,position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();

        mDrawerList.setItemChecked(position,true);
        mDrawLayout.closeDrawer(mDrawerList);
    }
    public static class MyFragment extends Fragment{
        public static final String ITEM_NUMBER_STRING = "item_number";

        public MyFragment(){

        }


        @Override
        public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_item,container,false);
            int i = getArguments().getInt(ITEM_NUMBER_STRING);
            String itemName = getResources().getStringArray(R.array.item_array)[i];
            ((TextView)(rootView.findViewById(R.id.textview))).setText(itemName);

            return rootView;
        }
    }
}