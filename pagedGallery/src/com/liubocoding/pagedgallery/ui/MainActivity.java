package com.liubocoding.pagedgallery.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liubocoding.pagedgallery.PagedGallery;
import com.liubocoding.pagedgallery.R;

public class MainActivity extends Activity {

    private int screenWidth = 0;
    private TextView mIndex = null;
    private PagedGallery mPagedGallery = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        final DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;

        mIndex = (TextView) findViewById(R.id.index);
        mPagedGallery = (PagedGallery) findViewById(R.id.pagedGallery);
        mPagedGallery.setAdapter(new PagedGalleryAdapter());
    }

    class PagedGalleryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_paged_gallery, null);
                holder = new Holder();
                holder.index = (TextView) convertView.findViewById(R.id.index);

                ViewGroup.LayoutParams vlp = convertView.getLayoutParams();
                if (vlp == null) {
                    vlp = new ViewGroup.LayoutParams(-1, -1);
                }
                // set an exact width value
                vlp.width = Math.round(screenWidth * 0.8f);
                convertView.setLayoutParams(vlp);

                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            holder.index.setText(String.format("Index:%d", position));

            return convertView;
        }

        class Holder {
            TextView index;
        }
    }

}
