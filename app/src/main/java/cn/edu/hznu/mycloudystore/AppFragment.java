package cn.edu.hznu.mycloudystore;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AppFragment extends Fragment
{
    private List<AppInfo> appInfoList = new ArrayList<>();
    private List<AppInfo> freshAppInfoList = new ArrayList<>();
    private List<AppInfo> optimizeAppInfoList = new ArrayList<>();
    public AppFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app, container,false);
        initApps();

        //recommentApp
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.app_recyclerview_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        AppAdapter appAdapter = new AppAdapter(appInfoList);
        recyclerView.setAdapter(appAdapter);

        //freshApp
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.app_recyclerview_item2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView2.setLayoutManager(layoutManager2);
        FreshAppAdapter freshAppAdapter = new FreshAppAdapter(freshAppInfoList);
        recyclerView2.setAdapter(freshAppAdapter);

        //optimize for iPhone X
        RecyclerView recyclerView3 = (RecyclerView) view.findViewById(R.id.app_recyclerview_item3);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        layoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView3.setLayoutManager(layoutManager3);
        OptimizeAppAdapter optimizeAppAdapter = new OptimizeAppAdapter(optimizeAppInfoList);
        recyclerView3.setAdapter(optimizeAppAdapter);

        return view;
    }
    private void initApps()
    {
        for (int i = 0; i < 1; i++)
        {
            AppInfo appTocaLife = new AppInfo("Toca Life: Pets", R.drawable.app_one_intro, "满足你对每种宠物的想象。");
            appInfoList.add(appTocaLife);
            AppInfo appWhiteYou = new AppInfo("White You", R.drawable.app_four_intro, "Someone like you, just like you.");
            appInfoList.add(appWhiteYou);
            AppInfo appMeiTu = new AppInfo("美图秀秀", R.drawable.app_three_intro, "二次元画风，为你加点萌");
            appInfoList.add(appMeiTu);
            AppInfo appMindNode5 = new AppInfo("MindNode 5", R.drawable.app_two_intro, "简洁易用， 图像化整理思路");
            appInfoList.add(appMindNode5);
        }

        //Fresh App
        AppInfo freshAppTocaLife = new AppInfo("Toca Life: Pets", R.drawable.app_one, "满足你对每种宠物的想象。", "6.00");
        freshAppInfoList.add(freshAppTocaLife);
        AppInfo freshAppWhiteYou = new AppInfo("White You", R.drawable.app_four, "Someone like you, just like you.", "18.00");
        freshAppInfoList.add(freshAppWhiteYou);
        AppInfo freshAppMeiTu = new AppInfo("美图秀秀", R.drawable.app_three, "二次元画风，为你加点萌", "获取");
        freshAppInfoList.add(freshAppMeiTu);
        /*AppInfo freshAppMindNode5 = new AppInfo("MindNode 5", R.drawable.app_two_intro, "简洁易用， 图像化整理思路", "1.00");
        freshAppInfoList.add(freshAppMindNode5);*/

        AppInfo optimizeAppTocaLife = new AppInfo("Toca Life: Pets", R.drawable.app_one, "满足你对每种宠物的想象。", "6.00");
        optimizeAppInfoList.add(optimizeAppTocaLife);
        AppInfo optimizeAppWhiteYou = new AppInfo("White You", R.drawable.app_four, "Someone like you, just like you.", "18.00");
        optimizeAppInfoList.add(optimizeAppWhiteYou);
        AppInfo optimizeAppMeiTu = new AppInfo("美图秀秀", R.drawable.app_three, "二次元画风，为你加点萌", "获取");
        optimizeAppInfoList.add(optimizeAppMeiTu);
    }

}
