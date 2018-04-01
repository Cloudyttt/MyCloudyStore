package cn.edu.hznu.mycloudystore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import static android.content.ContentValues.TAG;

public class UserFragment extends Fragment {
    Bundle fgbundle = new Bundle();
    private User user;
    private TextView username;
    private TextView userPhone;
    private Button btnWallet;
    private Button btnShoppingcar;
    private Button btnCoupon;
    private Button btnSetting;
    private Button btnLogout;

    private UserDataManager userDataManager;
    public UserFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        //获取控件
        username = view.findViewById(R.id.userName);
        userPhone = view.findViewById(R.id.userPhone);
        btnWallet = view.findViewById(R.id.btnWallet);
        btnShoppingcar = view.findViewById(R.id.btnShoppingcar);
        btnCoupon = view.findViewById(R.id.btnCoupon);
        btnSetting =view.findViewById(R.id.btnSetting);
        btnLogout = view.findViewById(R.id.btnLogout);
        //获取用户信息数据
        fgbundle = getArguments();//从activity传过来的Bundle
        if(fgbundle!=null){
            user = (User) fgbundle.getSerializable("userInfoToUserFragment");
            Log.i(TAG, "UserFragment中user接收到的信息: username = " + user.getUsername());
            username.setText(user.getUsername());
            userPhone.setText(user.getTelephone());

        }
        //修改密码按钮点击事件
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToRevise = new Intent(getContext(), ReviseInfoActivity.class);
                intentToRevise.putExtra("CurruserInfo",(Serializable)user);
                startActivity(intentToRevise);
            }
        });
        //建立本地数据库
        if (userDataManager == null)
        {
            userDataManager = new UserDataManager(getActivity());
            userDataManager.openDataBase();
        }
        //注销登录
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    /*public void onAttach(Activity activity) {
        super.onAttach(activity);
        titles = ((MainActivity) activity).getTitles();//通过强转成宿主activity，就可以获取到传递过来的数据
    }*/
}
