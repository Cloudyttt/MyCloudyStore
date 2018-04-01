package cn.edu.hznu.mycloudystore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cloudy on 2017/12/13.
 */

public class TodayFragment extends Fragment
{
    public TodayFragment() {
        // Required empty public constructor
    }
    private List<AppInfo> appInfoList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today,container,false);
        initFruits();   //初始化app数据
        TodayAppAdapter todayAppAdapter = new TodayAppAdapter(getActivity(),
                R.layout.list_item_today, appInfoList);
        ListView listView = view.findViewById(R.id.lv_today);
        listView.setAdapter(todayAppAdapter);
        return view;
    }
    private void initFruits()
    {
        for(int i = 0; i < 2; i++)
        {
            AppInfo app1 = new AppInfo("app1", "Game", R.drawable.app_store, "6.00", "12月21日 星期四", "今天");
            appInfoList.add(app1);
            AppInfo app2 = new AppInfo("app2", "Game", R.drawable.app_store, "6.00", "12月19日 星期三", "昨天");
            appInfoList.add(app2);
            AppInfo app3 = new AppInfo("app3", "Game", R.drawable.app_store, "6.00", "12月15日", "星期五");
            appInfoList.add(app3);
        }
    }
}