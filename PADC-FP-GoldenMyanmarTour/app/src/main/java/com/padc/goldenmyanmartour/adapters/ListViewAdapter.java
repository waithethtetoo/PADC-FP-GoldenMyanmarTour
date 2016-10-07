package com.padc.goldenmyanmartour.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;
import com.padc.goldenmyanmartour.views.holders.FestivalViewHolder;
import com.padc.goldenmyanmartour.views.holders.HotelViewHolder;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WT on 9/17/2016.
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private String[] roomDesc;
    private String[] charge;
    private String[] roomPhotos;
    private Context context;
    private static LayoutInflater inflater = null;
    private ControllerRoomItem controllerRoomItem;

    public ListViewAdapter(String[] roomDesc, String[] charge, String[] photos, ControllerRoomItem controllerItem) {
        this.roomDesc = roomDesc;
        this.charge = charge;
        this.roomPhotos = photos;
        inflater = LayoutInflater.from(GMTApp.getContext());
        this.controllerRoomItem = controllerItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item_design, parent, false);
        return new ViewHolder(itemView, controllerRoomItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String roomName = roomDesc[position];
        String roomCharges = charge[position];
        String roomPhoto = roomPhotos[position];
        holder.bindData(roomName, roomCharges, roomPhoto);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return roomDesc.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_room)
        ImageView ivRoom;
        @BindView(R.id.tv_room_name)
        TextView tvRoomName;
        @BindView(R.id.tv_room_price)
        TextView tvRoomPrice;

        ControllerRoomItem mController;

        public ViewHolder(View itemView, ControllerRoomItem controllerRoomItem) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mController = controllerRoomItem;
        }

        public void bindData(String descs, String charges, String photo) {

            tvRoomName.setText(descs);
            tvRoomPrice.setText(charges);

            Glide.with(ivRoom.getContext())
                    .load(photo)
                    .centerCrop()
                    .placeholder(R.drawable.gmtiicon)
                    .error(R.drawable.gmtiicon)
                    .into(ivRoom);
        }

    }


    public interface ControllerRoomItem {

    }

}