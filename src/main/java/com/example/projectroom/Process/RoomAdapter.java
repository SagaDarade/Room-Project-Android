package com.example.projectroom.Process;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.recyclerdbdemo.R;

import com.example.projectroom.R;
import com.example.projectroom.Room.DisplayRoom;
import com.example.projectroom.Room.RoomUpdate;

import java.util.List;

/**
 * RecyclerView.Adapter
 * RecyclerView.ViewHolder
 *
 * */


public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ProductViewHolder> {

    private Context mCtx;
    private int flg;
    private List<RoomPOJO> productList;

    public RoomAdapter(Context mCtx,int flg, List<RoomPOJO> productList) {
        this.mCtx = mCtx;
        this.flg=flg;
        this.productList = productList;
        System.out.println("Total Rooms: "+getItemCount());
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create View Holder
//        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_room_list_layout,parent,false);
        ProductViewHolder pholder = new ProductViewHolder(view,mCtx);
        return  pholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
//        AlertDialog ad = new AlertDialog;

        RoomPOJO product = productList.get(position);

        holder.tx_rno.setText(productList.get(position).getRno());
        holder.tx_rdep.setText(productList.get(position).getRdep());
        holder.tx_main.setText(productList.get(position).getRmain());
        holder.tx_rent.setText(productList.get(position).getRent());

        holder.ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flg==0) {
                    Intent i = new Intent(mCtx, RoomUpdate.class);
                    Bundle ban = new Bundle();
                    ban.putString("s1", productList.get(position).getRno());
                    ban.putString("s2", productList.get(position).getRdep());
                    ban.putString("s3", productList.get(position).getRmain());
                    ban.putString("s4", productList.get(position).getRent());
                    i.putExtras(ban);
                    mCtx.startActivity(i);
                }
//                else
//                {
//                    Intent i = new Intent(mCtx, DisplayRoom.class);
//                    Bundle ban = new Bundle();
//                    ban.putString("s1", productList.get(position).getRno());
//                    ban.putString("s2", productList.get(position).getRdep());
//                    ban.putString("s3", productList.get(position).getRmain());
//                    ban.putString("s4", productList.get(position).getRent());
//                    i.putExtras(ban);
//                    mCtx.startActivity(i);
//                    mCtx.startActivity(new Intent(mCtx, DisplayRoom.class));
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        //Returns size of the list
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView tx_rno, tx_rdep, tx_main, tx_rent;
        LinearLayout ly;

        public ProductViewHolder(@NonNull View itemView,final Context context) {
            super(itemView);
            tx_rno = itemView.findViewById(R.id.tv_RnoValue);
            tx_rdep = itemView.findViewById(R.id.tv_RDEPValue);
            tx_main = itemView.findViewById(R.id.tv_RMainValue);
            tx_rent = itemView.findViewById(R.id.tv_RentValue);
            ly = itemView.findViewById(R.id.id1);
        }
    }
}
