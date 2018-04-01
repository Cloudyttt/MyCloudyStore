package cn.edu.hznu.mycloudystore;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Cloudy on 2017/12/12.
 */

public class TodayAppAdapter extends ArrayAdapter {
    private int resourceId;
    public TodayAppAdapter(Context context, int textViewResourceId, List<AppInfo> objects)
    {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        //获取当前项的AppInfo实例
        AppInfo appInfo= (AppInfo) getItem(position);
        //为该子项加载要传入的布局
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView appTime = (TextView) view.findViewById(R.id.appTime);
        TextView appCurrentTime = (TextView)  view.findViewById(R.id.appCurrentTime);
        ImageView appIcon  = (ImageView) view.findViewById(R.id.appIcon);
        TextView appName = (TextView) view.findViewById(R.id.appName);
        TextView appType = (TextView) view.findViewById(R.id.appType);
        Button appPrice = (Button) view.findViewById(R.id.appPrice);
        //设置显示的文字，后将布局返回
        appTime.setText(appInfo.getAppTime());
        appCurrentTime.setText(appInfo.getAppCurTime());
        appIcon.setImageResource(appInfo.getAppIconID());
        appName.setText(appInfo.getAppName());
        appType.setText(appInfo.getAppType());
        appPrice.setText(appInfo.getAppPrice());
        return view;
    }
}
