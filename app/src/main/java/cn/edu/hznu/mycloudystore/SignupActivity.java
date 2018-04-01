package cn.edu.hznu.mycloudystore;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static cn.edu.hznu.mycloudystore.Constant.DATABASE_NAME;

public class SignupActivity extends AppCompatActivity
{
    private EditText username_signup;
    private EditText telephone_signup;
    private EditText password_signup;
    private EditText password_signup_check;
    private TextView btnToLogin;
    private Button btnSignup;
    private UserDataManager userDataManager;
    //private EditText verificationCode;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //掩藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.hide();
        }
        //获取布局控件
        username_signup = findViewById(R.id.username_signup);
        telephone_signup = findViewById(R.id.telephone_signup);
        password_signup = findViewById(R.id.password_signup);
        password_signup_check = findViewById(R.id.password_signup_check);
        btnSignup = findViewById(R.id.btnSignup);
        btnToLogin = findViewById(R.id.btnToLogin);
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intentToLogin = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intentToLogin);
                finish();
            }
        });
        //建立本地数据库
        if (userDataManager == null)
        {
            userDataManager = new UserDataManager(SignupActivity.this);
            userDataManager.openDataBase();
        }
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(checkUserInputFilled())
                {
                    //检查用户是否存在
                    int count = userDataManager.findUserByPhone(telephone_signup.getText().toString().trim());
                    if(count > 0)
                    {
                        Toast.makeText(SignupActivity.this, "用户已存在",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //检查用户两次输入密码是否一致
                    if(!password_signup.getText().toString().trim().equals(password_signup_check.getText().toString().trim()))
                    {
                        Toast.makeText(SignupActivity.this, password_signup.getText().toString().trim()+"密码输入不一致"+password_signup_check.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                        return;
                    }
                    User user = new User(username_signup.getText().toString().trim(),
                            telephone_signup.getText().toString().trim(),
                            password_signup.getText().toString().trim());
                    //新建用户信息
                    long flag = userDataManager.insertUserData(user);
                    if (flag == -1)
                    {
                        Toast.makeText(SignupActivity.this, "注册失败!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(SignupActivity.this, "注册成功！",Toast.LENGTH_SHORT).show();
                        //注册成功后跳转到登录界面
                        Intent intent_Register_to_Login = new Intent(SignupActivity.this, LoginActivity.class) ;
                        startActivity(intent_Register_to_Login);
                        finish();
                    }
                }
            }
        });

    }
    //检查用户输入是否有未填项
    public boolean checkUserInputFilled()
    {
        boolean isUsernameEmpty = TextUtils.isEmpty(username_signup.getText().toString().trim());
        boolean isTelephoneEmpty = TextUtils.isEmpty(telephone_signup.getText().toString().trim());
        boolean isPasswordEmpty = TextUtils.isEmpty(password_signup.getText().toString().trim());
        if(isUsernameEmpty || isTelephoneEmpty || isPasswordEmpty)
        {
            Toast.makeText(SignupActivity.this, "用户注册信息填写不全！", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }
}
