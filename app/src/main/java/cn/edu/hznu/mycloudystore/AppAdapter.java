package cn.edu.hznu.mycloudystore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cloudy on 2017/12/15.
 */

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {
    private List<AppInfo> mAppList;
    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView chosenAppName;
        TextView chosenAppIntro;
        ImageView chosenAppIntroImage;

        public ViewHolder(View view)
        {
            super(view);
            chosenAppName = (TextView) view.findViewById(R.id.chosenAppName);
            chosenAppIntro = (TextView) view.findViewById(R.id.chosenAppIntro);
            chosenAppIntroImage = (ImageView) view.findViewById(R.id.chosenAppIntroImage);
        }
    }

    public AppAdapter(List<AppInfo> appInfoList)
    {
        mAppList = appInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_carefullychosen_app,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        AppInfo appInfo = mAppList.get(position);
        holder.chosenAppName.setText(appInfo.getAppName());
        holder.chosenAppIntro.setText(appInfo.getAppIntro());
        holder.chosenAppIntroImage.setImageResource(appInfo.getImageIntroID());
    }

    @Override
    public int getItemCount()
    {
        return mAppList.size();
    }
}
