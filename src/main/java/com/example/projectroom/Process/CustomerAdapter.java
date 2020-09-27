package com.example.projectroom.Process;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectroom.Customer.Payment;
import com.example.projectroom.Customer.UpdateCustomer;
import com.example.projectroom.R;
import com.example.projectroom.Room.RoomUpdate;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustViewHolder> {
    private Context mCtx;
    private int flg;
    private List<CustPOJO> custList;

    public CustomerAdapter(Context mCtx, int flg, List<CustPOJO> custList) {
        this.mCtx = mCtx;
        this.flg = flg;
        this.custList = custList;
        System.out.println("Total Customers: "+getItemCount());
    }


    @NonNull
    @Override
    public CustViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_customer_list,parent,false);
        CustViewHolder cholder = new CustomerAdapter.CustViewHolder(view,mCtx);
        return cholder;
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.CustViewHolder holder, final int position) {
        CustPOJO product = custList.get(position);
        holder.tx_id.setText(""+custList.get(position).getCid());
        holder.tx_name.setText(custList.get(position).getcName());
        holder.tx_Cont.setText(custList.get(position).getcPh1());
        holder.tx_join.setText(custList.get(position).getcJoining());
        holder.tx_rNo.setText(custList.get(position).getRoomNo());

        final String rs = ""+custList.get(position).getCid()+") "+custList.get(position).getcName()
                +" "+custList.get(position).getcPh1()+" "+custList.get(position).getcJoining()+
                " "+custList.get(position).getRoomNo();

        holder.ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flg==0) {
                    Toast.makeText(mCtx, rs, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mCtx, UpdateCustomer.class);
                    Bundle ban = new Bundle();
                    ban.putString("c00cid", "" + custList.get(position).getCid());
                    ban.putString("c01Name", custList.get(position).getcName());
                    ban.putString("c02Addr", custList.get(position).getcAddress());
                    ban.putString("c03BDate", custList.get(position).getcBdate());
                    ban.putString("c04Ph1", custList.get(position).getcPh1());
                    ban.putString("c05Ph2", custList.get(position).getcPh2());
                    ban.putString("c06Email", custList.get(position).getcEmail());
                    ban.putString("c07Adhar", custList.get(position).getcAdhaar());
                    ban.putString("c08PAN", custList.get(position).getcPAN());
                    ban.putString("c09Jd", custList.get(position).getcJoining());
                    ban.putString("c10Ld", custList.get(position).getcLeaveing());
                    ban.putString("c11Mtr", "" + custList.get(position).getMtr());
                    ban.putString("c12Rno", custList.get(position).getRoomNo());

                    i.putExtras(ban);
                    mCtx.startActivity(i);
                }
                else if(flg==1){
                    Intent i = new Intent(mCtx, Payment.class);
                    Bundle ban = new Bundle();
                    ban.putString("c00cid", "" + custList.get(position).getCid());
                    ban.putString("c01Name", custList.get(position).getcName());
                    ban.putString("c11Mtr", "" + custList.get(position).getMtr());
                    ban.putString("c12Rno", custList.get(position).getRoomNo());

                    i.putExtras(ban);
                    mCtx.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return custList.size();
    }

    class CustViewHolder extends RecyclerView.ViewHolder{

        TextView tx_id,tx_name,tx_rNo, tx_Cont, tx_join;
        LinearLayout ly;

        public CustViewHolder(@NonNull View iv, final Context c) {
            super(iv);
            tx_id = iv.findViewById(R.id.tv_cid);
            tx_name = iv.findViewById(R.id.tv_name);
            tx_Cont = iv.findViewById(R.id.tv_contact);
            tx_join = iv.findViewById(R.id.tv_joining);
            tx_rNo = iv.findViewById(R.id.tv_rNum);
            ly = iv.findViewById(R.id.ly);
        }
    }
}
