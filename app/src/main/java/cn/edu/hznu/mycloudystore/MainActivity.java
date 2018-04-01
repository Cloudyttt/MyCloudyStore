package cn.edu.hznu.mycloudystore;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity  implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;
    private User user;
    private Bundle bundle = new Bundle();
    //碎片Fragment类
    private TodayFragment fg1;
    private AppFragment fg2;
    private SearchFragment fg3;
    private UserFragment fg4;
    private FragmentManager myManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //掩藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.hide();
        }

        //从登录活动中获取用户信息数据
        Intent intent = getIntent();
        user = (User)getIntent().getSerializableExtra("userInfo");
        Log.i(TAG, "从LoginActivity传递来，MainActivity中的user: userName = " + user.getUsername());
        //通过bundle将登录的用户数据传递给UserFragment
        bundle.putSerializable("userInfoToUserFragment",(Serializable)user);

       /* Log.i(TAG, "bundle.toString()： " + bundle.toString());
        User myuser = (User) bundle.getSerializable("userInfoToUserFragment");
        Log.i(TAG, "myuser： " + myuser.getUsername());*/
        myManager = getSupportFragmentManager();//获取FragmentManager
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        rb_channel = (RadioButton) findViewById(R.id.rb_channel);
        rb_channel.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        FragmentTransaction myTransaction = myManager.beginTransaction();//开启一个事物
        hideAllFragment(myTransaction);
        switch (checkedId){
            case R.id.rb_channel:
                if(fg1 == null)
                {
                    fg1 = new TodayFragment();
                    myTransaction.add(R.id.ly_content,fg1);
                }
                else
                {
                    myTransaction.show(fg1);
                }
                break;
            case R.id.rb_message:
                if(fg2 == null)
                {
                    fg2 = new AppFragment();
                    myTransaction.add(R.id.ly_content,fg2);
                }
                else
                {
                    myTransaction.show(fg2);
                }
                break;
            case R.id.rb_better:
                if(fg3 == null)
                {
                    fg3 = new SearchFragment();
                    myTransaction.add(R.id.ly_content,fg3);
                }
                else
                {
                    myTransaction.show(fg3);
                }
                break;
            case R.id.rb_setting:
                if(fg4 == null)
                {
                    fg4 = new UserFragment();
                    myTransaction.add(R.id.ly_content,fg4);
                    fg4.setArguments(bundle);
                }
                else
                {
                    myTransaction.show(fg4);
                }
                break;
        }
        myTransaction.commit();//提交事务
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction)
    {
        if(fg1 != null)
        {
            fragmentTransaction.hide(fg1);
        }
        if(fg2 != null)
        {
            fragmentTransaction.hide(fg2);
        }
        if(fg3 != null)
        {
            fragmentTransaction.hide(fg3);
        }
        if(fg4 != null)
        {
            fragmentTransaction.hide(fg4);
        }
    }
}
