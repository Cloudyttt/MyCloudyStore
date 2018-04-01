package cn.edu.hznu.mycloudystore;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class ReviseInfoActivity extends AppCompatActivity {
    private EditText oldPass;
    private EditText newPass;
    private EditText newPass_check;
    private Button btnRevise;
    private UserDataManager userDataManager;
    private User myUser;
    //private EditText verificationCode;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_info);
        //掩藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.hide();
        }
        //获取布局控件
        oldPass = findViewById(R.id.revise_oldPass);
        newPass = findViewById(R.id.revise_newPass);
        newPass_check = findViewById(R.id.revise_newPass_check);
        btnRevise = findViewById(R.id.btnRevise);

        //接收来自UserFragment的当前用户信息
        Intent intentToRevise = getIntent();
        myUser = (User)getIntent().getSerializableExtra("CurruserInfo");
        Log.i(TAG, "从UserFragment传递来，ReviseInfoActivity中的user: userName = " + myUser.getUsername());

        //建立本地数据库
        if (userDataManager == null)
        {
            userDataManager = new UserDataManager(ReviseInfoActivity.this);
            userDataManager.openDataBase();
        }
        btnRevise.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(checkUserInputFilled())
                {
                    //检查新旧密码是否一致,检查旧密码是否输入正确
                    if(!(oldPass.getText().toString().trim().equals(newPass.getText().toString().trim()))
                            &&(oldPass.getText().toString().trim().equals(myUser.getPassword())))
                    {
                        //检查用户两次输入密码是否一致
                        if(!newPass.getText().toString().trim().equals(newPass_check.getText().toString().trim()))
                        {
                            Toast.makeText(ReviseInfoActivity.this, newPass.getText().toString().trim()+"密码输入不一致"+newPass_check.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                            return;
                        }
                        User reviseUser = new User(myUser.getTelephone(),newPass_check.getText().toString().trim());
                        //修改用户信息
                        Log.i(TAG, "修改后的用户信息，手机号： "+ reviseUser.getTelephone() + ";新密码： " + reviseUser.getPassword());
                        boolean flag = userDataManager.updateUserData(reviseUser);
                        if (flag == false)
                        {
                            Toast.makeText(ReviseInfoActivity.this, "修改失败!",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(ReviseInfoActivity.this, "修改成功，请重新登录！",Toast.LENGTH_SHORT).show();
                            //注册成功后跳转到登录界面
                            Intent intent_Revise_to_Login = new Intent(ReviseInfoActivity.this, LoginActivity.class);
                            intent_Revise_to_Login.putExtra("passwordRevised",1);
                            startActivity(intent_Revise_to_Login);
                            finish();
                        }
                    }
                    else if(!(oldPass.getText().toString().trim().equals(myUser.getPassword())))
                    {
                        Toast.makeText(ReviseInfoActivity.this, "旧密码输入错误",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        Toast.makeText(ReviseInfoActivity.this, "新密码不能与旧密码相同",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });

    }
    //检查用户输入是否有未填项
    public boolean checkUserInputFilled()
    {
        boolean isOldPass = TextUtils.isEmpty(oldPass.getText().toString().trim());
        boolean isNewPass = TextUtils.isEmpty(newPass.getText().toString().trim());
        boolean isNewPass_check = TextUtils.isEmpty(newPass_check.getText().toString().trim());
        if(isOldPass || isNewPass || isNewPass_check)
        {
            Toast.makeText(ReviseInfoActivity.this, "修改密码信息填写不全！", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }
}
