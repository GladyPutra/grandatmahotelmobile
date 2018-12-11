package com.example.gladyputra.gahmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gladyputra.gahmobile.Activity.DetilReservasiActivity;
import com.example.gladyputra.gahmobile.Activity.ProfileActivity;
import com.example.gladyputra.gahmobile.Activity.RoomDetailActivity;

import org.w3c.dom.Text;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder>{

    List<Room> roomList;
    Context mCtx;
    int resources;
    private static final  String KEY_ID_KAMAR = "keyidkamar";

    public RoomAdapter(List<Room> roomList, Context mCtx, int resources) {
        this.roomList = roomList;
        this.mCtx = mCtx;
        this.resources=resources;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mCtx).inflate(R.layout.list_kamar,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Room room = roomList.get(position);
        holder.txtnama00.setText(room.getNama());
        holder.txtkamar00.setText(room.getKamar());
        holder.txtstatus00.setText(room.getStatus());
        holder.txtharga00.setText(String.valueOf(room.getHarga()));
        holder.imageView00.setImageDrawable(mCtx.getResources().getDrawable(room.getImage()));

        holder.item_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle extras = new Bundle();
//                extras.putString("id_kamar",holder.txtnama00.getText().toString());

                Intent intent = new Intent(mCtx, RoomDetailActivity.class);
//                intent.putExtras(extras);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtnama00,txtkamar00,txtstatus00,txtharga00;
        ImageView imageView00;
        RelativeLayout item_order;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.getContext();

            txtnama00 = (TextView) itemView.findViewById(R.id.txtnama00);
            txtkamar00 = (TextView) itemView.findViewById(R.id.txtkamar00);
            txtstatus00 = (TextView) itemView.findViewById(R.id.txtstatus00);
            txtharga00 = (TextView) itemView.findViewById(R.id.txtharga00);
            imageView00 = (ImageView) itemView.findViewById(R.id.imageView00);
            item_order = (RelativeLayout) itemView.findViewById(R.id.item_room);

        }
    }
}