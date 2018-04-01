package cn.edu.hznu.mycloudystore;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cloudy on 2017/12/18.
 */

public class FreshAppAdapter extends RecyclerView.Adapter<FreshAppAdapter.ViewHolder>
{
    private List<AppInfo> freshAppList;
    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView freshAppName;
        TextView freshAppIntro;
        ImageView freshAppIcon;
        Button freshAppPrice;

        TextView freshAppName2;
        TextView freshAppIntro2;
        ImageView freshAppIcon2;
        Button freshAppPrice2;

        TextView freshAppName3;
        TextView freshAppIntro3;
        ImageView freshAppIcon3;
        Button freshAppPrice3;

        public ViewHolder(View view)
        {
            super(view);
            freshAppName = (TextView) view.findViewById(R.id.app_appName);
            freshAppIntro = (TextView) view.findViewById(R.id.app_appIntro);
            freshAppIcon = (ImageView) view.findViewById(R.id.app_appIcon);
            freshAppPrice = (Button) view.findViewById((R.id.app_appPrice));

            freshAppName2 = (TextView) view.findViewById(R.id.app_appName2);
            freshAppIntro2 = (TextView) view.findViewById(R.id.app_appIntro2);
            freshAppIcon2 = (ImageView) view.findViewById(R.id.app_appIcon2);
            freshAppPrice2 = (Button) view.findViewById((R.id.app_appPrice2));

            freshAppName3 = (TextView) view.findViewById(R.id.app_appName3);
            freshAppIntro3 = (TextView) view.findViewById(R.id.app_appIntro3);
            freshAppIcon3= (ImageView) view.findViewById(R.id.app_appIcon3);
            freshAppPrice3 = (Button) view.findViewById((R.id.app_appPrice3));
        }
    }

    public FreshAppAdapter(List<AppInfo> appInfoList)
    {
        freshAppList = appInfoList;
    }

    @Override
    public FreshAppAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recommend_app,parent,false);
        FreshAppAdapter.ViewHolder holder = new FreshAppAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FreshAppAdapter.ViewHolder holder, int position)
    {
        AppInfo appInfo = freshAppList.get(position);
        holder.freshAppName.setText(appInfo.getAppName());
        holder.freshAppIntro.setText(appInfo.getAppIntro());
        holder.freshAppIcon.setImageResource(appInfo.getAppIconID());
        holder.freshAppPrice.setText(appInfo.getAppPrice());

        AppInfo appInfo2 = freshAppList.get((position + 1) % 3);
        holder.freshAppName2.setText(appInfo2.getAppName());
        holder.freshAppIntro2.setText(appInfo2.getAppIntro());
        holder.freshAppIcon2.setImageResource(appInfo2.getAppIconID());
        holder.freshAppPrice2.setText(appInfo2.getAppPrice());

        AppInfo appInfo3 = freshAppList.get((position + 2) % 3);
        holder.freshAppName3.setText(appInfo3.getAppName());
        holder.freshAppIntro3.setText(appInfo3.getAppIntro());
        holder.freshAppIcon3.setImageResource(appInfo3.getAppIconID());
        holder.freshAppPrice3.setText(appInfo3.getAppPrice());

    }

    @Override
    public int getItemCount()
    {
        return freshAppList.size();
    }
}
