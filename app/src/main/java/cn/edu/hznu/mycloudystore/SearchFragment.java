package cn.edu.hznu.mycloudystore;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SearchFragment extends Fragment
{
    private EditText tvSearch;
    private TextView appitem1;
    private TextView appitem2;
    private TextView appitem3;
    private TextView appitem4;
    private LinearLayout results;
    private LinearLayout listitem_recommend;
    private ImageButton btnClose;
    private TextView searchAppName;
    private TextView searchAppIntro;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_search, container, false);

        appitem1 = (TextView) view.findViewById(R.id.appitem1);
        appitem2 = (TextView) view.findViewById(R.id.appitem2);
        appitem3 = (TextView) view.findViewById(R.id.appitem3);
        appitem4 = (TextView) view.findViewById(R.id.appitem4);
        btnClose = (ImageButton) view.findViewById(R.id.btnClose);
        listitem_recommend = (LinearLayout) view.findViewById(R.id.listitem_recommend);
        tvSearch = (EditText) view.findViewById(R.id.tvSearch);
        searchAppName = (TextView) view.findViewById(R.id.searchAppName);
        searchAppIntro = (TextView) view.findViewById(R.id.searchAppIntro);
        results = (LinearLayout) view.findViewById(R.id.search_result);
        results.setVisibility(View.INVISIBLE);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listitem_recommend.setVisibility(View.VISIBLE);
                results.setVisibility(View.GONE);
                tvSearch.setText("");
                btnClose.setVisibility(View.INVISIBLE);
            }
        });
        appitem1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listitem_recommend.setVisibility(View.GONE);
                results.setVisibility(View.VISIBLE);
                tvSearch.setText("丛林大逃杀");
                searchAppName.setText("丛林大逃杀");
                searchAppIntro.setText("校服萌妹空降120战场");
                btnClose.setVisibility(View.VISIBLE);
            }
        });
        appitem2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listitem_recommend.setVisibility(View.GONE);
                results.setVisibility(View.VISIBLE);
                tvSearch.setText("火狐浏览器");
                searchAppName.setText("火狐浏览器");
                searchAppIntro.setText("快速且私密的浏览器");
                btnClose.setVisibility(View.VISIBLE);
            }
        });
        appitem3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listitem_recommend.setVisibility(View.GONE);
                results.setVisibility(View.VISIBLE);
                tvSearch.setText("微信");
                searchAppName.setText("微信");
                searchAppIntro.setText("社交");
                btnClose.setVisibility(View.VISIBLE);
            }
        });
        appitem4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listitem_recommend.setVisibility(View.GONE);
                results.setVisibility(View.VISIBLE);
                tvSearch.setText("智学网");
                searchAppName.setText("智学网");
                searchAppIntro.setText("教育");
                btnClose.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }
}
