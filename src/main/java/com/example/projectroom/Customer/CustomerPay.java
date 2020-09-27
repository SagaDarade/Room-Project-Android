package com.example.projectroom.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import com.example.projectroom.Process.AsyncResponse;
import com.example.projectroom.Process.CustPOJO;
import com.example.projectroom.Process.CustomerAdapter;
import com.example.projectroom.Process.GetCustProcess;
import com.example.projectroom.Process.GetCustomers;
import com.example.projectroom.Process.GetRoomProcess;
import com.example.projectroom.Process.RoomAdapter;
import com.example.projectroom.Process.RoomPOJO;
import com.example.projectroom.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerPay extends AppCompatActivity {

    Button btn;
    String result;

    RecyclerView recycler;
    CustomerAdapter adapter;
    List<CustPOJO> cList;
    //List<CustPOJO> availabel;
    Context ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_customers);

        btn = findViewById(R.id.btnFetch);
        cList = new ArrayList<>();
        //availabel=new ArrayList<>();
        recycler = findViewById(R.id.recView);
        recycler.setLayoutManager(new LinearLayoutManager(this));


        GetCustomers getCustomers = new GetCustomers(CustomerPay.this, "allCust",
                new AsyncResponse() {
                    @Override
                    public void processFinish(Object output) {
                        result = (String) output;
                        System.out.println("Cust. Interface: "+result);
                        GetCustProcess getCustProcess = new GetCustProcess(result.trim());
                        cList = getCustProcess.getclist();
                        adapter = new CustomerAdapter(getApplicationContext(),1, cList);
                        recycler.setHasFixedSize(true);
                        recycler.setAdapter(adapter);
                    }
                });
        getCustomers.execute();
    }
}
