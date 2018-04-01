package cn.edu.hznu.mycloudystore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static android.content.ContentValues.TAG;

import java.io.Serializable;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

public class LoginActivity extends AppCompatActivity
{
    private int isRevisePassword = 0;//判断用户当前是否从修改密码界面跳转到登录界面
    private UserDataManager.UserDatabaseHelper dbHelper;
    private static User user;
    private EditText telephone_login;
    private EditText password_login;
    private TextView tvToSignUp;
    private Button btnLogin;
    private CheckBox rememberPass;
    private UserDataManager mUserDataManager;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.hide();
        }
        Intent intent_revisePass = getIntent();
        isRevisePassword = intent_revisePass.getIntExtra("passwordRevised",0);
        Log.i(TAG, "判断是否修改了密码: isRevisePassword = " + isRevisePassword);
        //获取布局控件
        telephone_login = findViewById(R.id.telephone_login);
        password_login = findViewById(R.id.password_login);
        tvToSignUp = findViewById(R.id.btnToSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        rememberPass = findViewById(R.id.rememberPass);
        //直接跳转到登录界面
        tvToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intentToLogin = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intentToLogin);
                finish();
            }
        });
        //建立本地数据库
        if (mUserDataManager == null)
        {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
        //记住密码
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = pref.getBoolean("rememberPass", false);
        if(isRemember)
        {
            //将登录手机号和密码设置到文本框中
            String username = pref.getString("username", "");
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            telephone_login.setText(account);
            password_login.setText(password);
            user = new User(username, account, password);
            rememberPass.setChecked(true);
        }
        //如果用户刚刚修改完密码，则清空记住的密码
        if(isRevisePassword == 1)
        {
            rememberPass.setChecked(false);
            password_login.setText("");
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (isPhoneAndPwdValid())
                {
                    //mUserDataManager.fetchAllUserDatas();
                    //获取当前输入的用户名和密码信息
                    String account = telephone_login.getText().toString().trim();
                    String password = password_login.getText().toString().trim();
                    int result = mUserDataManager.findUserByPhoneAndPwd(account, password);
                    isRevisePassword = 0;
                    if(result==1)//返回1说明用户名和密码均正确
                    {
                        //保存用户名和密码
                        editor = pref.edit();
                        if(rememberPass.isChecked())
                        {
                            Log.i(TAG, "rememberPass.isChecked() = true");
                            String name = mUserDataManager.getUernameByPhone(account);
                            editor.putBoolean("rememberPass", true);
                            editor.putString("account", account);
                            editor.putString("password", password);
                            editor.putString("username", name);
                            user = new User(name, account, password);
                        }
                        else
                        {
                            Log.i(TAG, "rememberPass.isChecked() = false");
                            editor.putBoolean("rememberPass", false);
                            editor.clear();
                            user = new User(mUserDataManager.getUernameByPhone(account), account, password);
                        }
                        editor.apply();

                        //切换Login Activity至UserFragment
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("userInfo",(Serializable)user);
                        Log.i(TAG, "登录时输入的用户名: userName = " + user.getUsername());
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();//登录成功提示

                    }
                    else if(result == 0)
                    {
                        Toast.makeText(LoginActivity.this, "登录失败！",Toast.LENGTH_SHORT).show();  //登录失败提示
                    }
                }
            }
        });
    }
    public boolean isPhoneAndPwdValid()
    {
        if (TextUtils.isEmpty(telephone_login.getText().toString()) || TextUtils.isEmpty(password_login.getText().toString()))
        {
            Toast.makeText(this, "手机号或密码为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }
    @Override
    protected void onResume()
    {
        if (mUserDataManager == null)
        {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
        super.onResume();
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
    @Override
    protected void onPause()
    {
        if (mUserDataManager != null)
        {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }
    public User getUserInfo()
    {
        return user;
    }
}
