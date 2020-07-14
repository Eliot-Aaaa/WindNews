package com.eliot.news.mynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.eliot.news.mynews.fragment.EmptyFragment;
import com.eliot.news.mynews.fragment.NewsFragment;

public class MainActivity extends AppCompatActivity {

    long lastbacktime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTabHost tabHost = findViewById(R.id.tab_Host);
        String[] titles = getResources().getStringArray(R.array.tab_title);
        int[] icons = new int[]{R.drawable.news_selector, R.drawable.reading_selector, R.drawable.video_selector, R.drawable.topic_selector, R.drawable.mine_selector};
        Class[] classes = new Class[]{NewsFragment.class, EmptyFragment.class, EmptyFragment.class, EmptyFragment.class, EmptyFragment.class};
        tabHost.setup(this, getSupportFragmentManager(), R.id.content);
        for (int i=0;i<titles.length;i++)
        {
            TabHost.TabSpec tmp = tabHost.newTabSpec(""+i);
            tmp.setIndicator(getEveryView(this, titles, icons,i));
            tabHost.addTab(tmp, classes[i], null);
        }
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener()
        {
            @Override
            public void onTabChanged(String tabId)
            {
                Log.i("测试7：", "tabId = "+tabId);
            }
        });
    }

    public View getEveryView(Context context, String[] titles, int[] icons, int index)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View title_view = inflater.inflate(R.layout.item_title, null);
        TextView title = (TextView) title_view.findViewById(R.id.title);
        ImageView icon = (ImageView) title_view.findViewById(R.id.icon);
        title.setText(titles[index]);
        icon.setImageResource(icons[index]);
        return title_view;
    }

    @Override
    public void onBackPressed()
    {
        long now = System.currentTimeMillis();
        if (now - lastbacktime < 1000)
        {
            finish();
        }
        else
            Toast.makeText(MainActivity.this, "再按一次退出网易新闻", Toast.LENGTH_SHORT).show();
        lastbacktime = now;
    }
}
