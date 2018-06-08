package com.example.huar.myapplication.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huar.myapplication.Fragment.CheckFragment;
import com.example.huar.myapplication.Fragment.UserFragment;
import com.example.huar.myapplication.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    SearchView searchView;

    private LinearLayout tab1;
    private LinearLayout tab2;
    private ImageView tab1image;
    private ImageView tab2image;

    private TextView tab1text;
    private TextView tab2text;

    private Fragment tab1fragment = null;
    private Fragment tab2fragment = null;

    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDate(); //初始化控件
        initEvent();//设置响应事件
        select(0); //改变图标、字体颜色
    }

    private void select(int i) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);//隐藏别的Fragment
        clearItem(); //重置按钮
        switch (i)
        {
            case 0:
                if(tab1fragment==null){
                    tab1fragment = new CheckFragment();
                    transaction.add(R.id.fragment_tab,tab1fragment);
                }else{
                    transaction.show(tab1fragment);
                }
                tab1image.setImageResource(R.drawable.view_app_list_pr);
                tab1text.setTextColor(android.graphics.Color.rgb(24,128,244));
                break;
            case 1:
                if(tab2fragment==null){
                    tab2fragment = new UserFragment();
                    transaction.add(R.id.fragment_tab,tab2fragment);
                }else{
                    transaction.show(tab2fragment);
                }
                tab2image.setImageResource(R.drawable.view_user_pr);
                tab2text.setTextColor(android.graphics.Color.rgb(24,128,244));
                break;
        }
        transaction.commit();
    }

    private void clearItem() {
        tab1image.setImageResource(R.drawable.view_app_list_no);
        tab2image.setImageResource(R.drawable.view_user_no);


        tab1text.setTextColor(android.graphics.Color.rgb(43,51,59));
        tab2text.setTextColor(android.graphics.Color.rgb(43,51,59));
    }

    /**
     * 用fragmentTransaction的show和hide方法隐藏和显示Fragment
     * 用的才加载. 一旦加载就不删除. 切换只用hide和show
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (tab1fragment != null) {
            transaction.hide(tab1fragment);
        }
        if (tab2fragment != null) {
            transaction.hide(tab2fragment);
        }
    }

    private void initEvent() {
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
    }

    private void initDate() { 
        tab1 = (LinearLayout)findViewById(R.id.tab1);
        tab2 = (LinearLayout)findViewById(R.id.tab2);
        
        tab1image = (ImageView)findViewById(R.id.tab1image);
        tab2image = (ImageView)findViewById(R.id.tab2image);
        
        tab1text = (TextView)findViewById(R.id.tab1text);
        tab2text = (TextView)findViewById(R.id.tab2text);
        

    }

    //菜单工具栏 搜索
    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        //使用菜单填充器获取menu下的菜单资源文件
        getMenuInflater().inflate(R.menu.serch_share_menu, menu);
        //获取搜索的菜单组件
        MenuItem menuItem = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        //设置搜索的事件
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast t = Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT);
               // t.setGravity(Gravity.TOP, 0, 0);
               // t.show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tab1:
                select(0);
                break;
            case R.id.tab2:
                select(1);
                break;
        }
    }
}
