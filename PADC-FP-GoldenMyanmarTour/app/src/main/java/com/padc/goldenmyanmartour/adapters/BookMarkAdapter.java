package com.padc.goldenmyanmartour.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.BookmarkVO;

import java.util.List;

/**
 * Created by WT on 9/16/2016.
 */
public class BookMarkAdapter extends BaseAdapter {

    private List<BookmarkVO> bookmarkList;
    private LayoutInflater inflater;
    private List<String> nameList;

    public BookMarkAdapter(List<String> list) {
        this.nameList = list;
        inflater = LayoutInflater.from(GMTApp.getContext());
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_item_result, parent, false);

            TextView tvResult = (TextView) convertView.findViewById(R.id.tv_result);
            tvResult.setText(nameList.get(position));
//            tvResult.setText("Pyin-Oo-Lwin");
            ImageView ivResult = (ImageView) convertView.findViewById(R.id.iv_result);

        }
        return convertView;
    }
}
