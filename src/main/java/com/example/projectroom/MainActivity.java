package com.example.projectroom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View ;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectroom.Customer.CustomerPay;
import com.example.projectroom.Customer.CustomerStore;
import com.example.projectroom.Customer.Payment;
import com.example.projectroom.Domain.MainDomain;
import com.example.projectroom.Interfare.ServerCall;
import com.example.projectroom.Room.DisplayRoom;
import com.example.projectroom.Room.GetAllRooms;
import com.example.projectroom.Room.GetAvailRooms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText ed_rno,ed_deposit,ed_maintain,ed_rent;
    Button b_save, b_ext, b_select, b_clr, b_showAll, b_book;

//    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_rno = findViewById(R.id.ed_rno);
        ed_deposit = findViewById(R.id.ed_deposit);
        ed_maintain = findViewById(R.id.ed_maintain);
        ed_rent = findViewById(R.id.ed_rent);

        b_save = findViewById(R.id.btn_save);
        b_ext = findViewById(R.id.btn_exit);
        b_select = findViewById(R.id.btn_select);
        b_clr = findViewById(R.id.btn_clr);
        b_showAll = findViewById(R.id.btn_showAll);
        b_book = findViewById(R.id.btn_Book);

        b_save.setOnClickListener(this);
        b_ext.setOnClickListener(this);
        b_select.setOnClickListener(this);
        b_book.setOnClickListener(this);
        b_clr.setOnClickListener(this);
        b_showAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.findViewById(R.id.btn_save) == b_save)     //Save Data
        {
            if(ed_deposit.getText().toString().equals("")||
                    ed_maintain.getText().toString().equals("")||
                    ed_rent.getText().toString().equals(""))
            {
                Toast.makeText(this,"All Fields Required",Toast.LENGTH_LONG).show();
                if(ed_rno.getText().toString().equals("")){
                    ed_rno.requestFocus();
                }
                else if(ed_deposit.getText().toString().equals("")){
                    ed_deposit.requestFocus();
                }
                else if(ed_maintain.getText().toString().equals("")){
                    ed_maintain.requestFocus();
                }
                else{
                    ed_rent.requestFocus();
                }
            }
            else {
                String Rno = ed_rno.getText().toString().trim();
                String Deposit = ed_deposit.getText().toString().trim();
                String Maintenance = ed_maintain.getText().toString().trim();
                String Rent = ed_rent.getText().toString().trim();
//                setRoom(Rno, Deposit, Maintenance, Rent);
                ServerCall scall = new ServerCall(MainActivity.this);
                scall.execute("addRoom",Rno,Deposit,Maintenance,Rent);
                ClearFields();
            }
        }
        if(v.findViewById(R.id.btn_exit) == b_ext)      //Close Screen
        {
            System.exit(0);
        }

        if(v.findViewById(R.id.btn_Book) == b_book)      //Close Screen
        {
            startActivity(new Intent(this, DisplayRoom.class));
//            startActivity(new Intent(this, CustomerStore.class));
        }

        if(v.findViewById(R.id.btn_select) == b_select)      //for available rooms
        {
//            Toast.makeText(this,"Select Room btn",Toast.LENGTH_SHORT).show();
//            Intent i=new Intent(this, GetAllRooms.class);
//            i.putExtra("flag",1);
//            startActivity(i);
            startActivity(new Intent(this, GetAvailRooms.class));
        }

        if(v.findViewById(R.id.btn_showAll) == b_showAll)      //for all rooms
        {
//            Intent i=new Intent(this, GetAllRooms.class);
//            i.putExtra("flag",0);
//            startActivity(i);
            startActivity(new Intent(this, GetAllRooms.class));
        }

        if(v.findViewById(R.id.btn_clr) == b_clr)      //Close Screen
        {
            ClearFields();
        }
    }

    public void Pay(View v) {
        startActivity(new Intent(this, CustomerPay.class));
    }

    public void ClearFields()
    {
        ed_rno.setText(null);        ed_deposit.setText(null);
        ed_maintain.setText(null);        ed_rent.setText(null);
        ed_rno.requestFocus();
    }

    public void setRoom(final String rno, final String dep, final String maint, final String rent)
    {
        class SendRoomData extends AsyncTask<String, Void, String>
        {
            ProgressDialog alert;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                alert = new ProgressDialog(MainActivity.this);
                alert.setTitle("TASK");
                alert.setMessage("Saving Data");
                alert.show();
            }

            @Override
            protected String doInBackground(String... params)
            {
                try
                {
                    String Rno = rno;
                    String Deposit = dep;
                    String Maintenance = maint;
                    String Rent = rent;

                    String url = MainDomain.getDomain()+"addRoom.php";

                    URL addURL = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) addURL.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);

                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufWri = new BufferedWriter(new OutputStreamWriter
                            (outputStream,"UTF-8"));

                    String post_data = URLEncoder.encode("rno","UTF-8")+"="+
                            URLEncoder.encode(Rno,"UTF-8")+"&"+
                            URLEncoder.encode("RoomDep","UTF-8")+"="+
                            URLEncoder.encode(Deposit,"UTF-8")+"&"+
                            URLEncoder.encode("RoomMaintain","UTF-8")+"="+
                            URLEncoder.encode(Maintenance,"UTF-8")+"&"+
                            URLEncoder.encode("RoomRent","UTF-8")+"="+
                            URLEncoder.encode(Rent,"UTF-8");

                    bufWri.write(post_data);
                    bufWri.flush();
                    bufWri.close();
                    outputStream.close();

                    InputStream instream = con.getInputStream();
                    BufferedReader bufRead = new BufferedReader(new InputStreamReader
                            (instream,"iso-8859-1"));

                    String result="", line="";
                    while((line = bufRead.readLine()) != null)
                    {
                        result += line;
                    }
                    bufRead.close();
                    instream.close();
                    con.disconnect();
                    if(result.startsWith("ï»¿ï»¿")) {
                        result = result.substring(6);
                    }
                    return result;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
//                alert.setMessage(s);
//                alert.show();
                alert.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                ClearFields();
            }
        }
        SendRoomData sd = new SendRoomData();
        sd.execute();
    }
}



