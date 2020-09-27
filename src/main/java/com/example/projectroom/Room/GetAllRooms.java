package com.example.projectroom.Room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectroom.Domain.MainDomain;
import com.example.projectroom.Process.AsyncResponse;
import com.example.projectroom.Process.GetRoomProcess;
import com.example.projectroom.Process.GetRooms;
import com.example.projectroom.Process.RoomAdapter;
import com.example.projectroom.Process.RoomPOJO;
import com.example.projectroom.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetAllRooms extends AppCompatActivity {

    Button btn;
    String result;

    RecyclerView recycler;
    RoomAdapter adapter;
    List<RoomPOJO> productList;
    List<RoomPOJO> availabel;
    Context ct;
    String arg = "all";
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_rooms);

/*        Bundle ban = getIntent().getExtras();
        flag = ban.getInt("flag");*/

        btn = findViewById(R.id.btnFetch);
        productList = new ArrayList<>();
        availabel=new ArrayList<>();
        recycler = findViewById(R.id.recView);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        GetRooms getRooms = new GetRooms(GetAllRooms.this,arg,new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                result = (String) output;
                System.out.println("MainAct: " + result);
                GetRoomProcess getRoomProcess = new GetRoomProcess(result.trim());
                productList = getRoomProcess.getPlist();
//                if(flag==1) {
//                    for (RoomPOJO r : productList) {
//                        if (r.getFlg() == 0) {
//                            availabel.add(r);
//                        }
//                    }
//                    adapter = new RoomAdapter(getApplicationContext(), availabel);
//                }
//                else {
                adapter = new RoomAdapter(getApplicationContext(), 0, productList);
//            }
                recycler.setHasFixedSize(true);
                recycler.setAdapter(adapter);
            }
        });
        getRooms.execute();


//        RoomListDemo();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetRooms getRooms = new GetRooms(GetAllRooms.this,arg,new AsyncResponse() {
                    @Override
                    public void processFinish(Object output) {
                        result = (String) output;
                        System.out.println("MainAct: "+result);
                        GetRoomProcess getRoomProcess = new GetRoomProcess(result.trim());
                        productList = getRoomProcess.getPlist();
                        adapter = new RoomAdapter(getApplicationContext(),0, productList);
                        recycler.setHasFixedSize(true);
                        recycler.setAdapter(adapter);
                    }
                });
                getRooms.execute();
            }
        });
    }

    public void RoomListDemo()
    {
        class ClsRoomList extends AsyncTask {
            Context c;

            ProgressDialog progressDialog;
            List<RoomPOJO> plist;
            String result;
            public AsyncResponse delegate = null;//Call back interface

            public ClsRoomList(AsyncResponse asyncResponse) {

                delegate = asyncResponse;
                plist = new ArrayList<>();
            }

            public List<RoomPOJO>getPlist(){
                return plist;
            }

            public String getResult(){return result;}

            private void progress()
            {
                progressDialog = new ProgressDialog(GetAllRooms.this);
                progressDialog.setTitle("Syncing...");
                progressDialog.setMessage("Please wait !");
                progressDialog.show();
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                 progress();
            }

            @Override
            protected Object doInBackground(Object[] objects) {

                String myurl= MainDomain.getDomain()+"viewAllRooms.php";
                System.out.println("2nd: "+myurl);
                try
                {
                    URL url=new URL(myurl);
                    HttpURLConnection hcon= (HttpURLConnection) url.openConnection();
                    hcon.setRequestMethod("POST");
                    hcon.setDoOutput(true);
                    hcon.setDoInput(true);

                    OutputStream ostream=hcon.getOutputStream();
                    BufferedWriter writer=new BufferedWriter
                            (new OutputStreamWriter(ostream,"UTF-8"));
                    String postdata="";
                    writer.write(postdata);
                    writer.flush();
                    writer.close();
                    ostream.close();

                    InputStream inputStream=hcon.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader
                            (inputStream,"iso-8859-1"));
//                    (new InputStreamReader(inputStream));
                    result="";
                    String line="";
                    while((line=reader.readLine())!=null) {
                        result+=line;
                    }
                    reader.close();
                    inputStream.close();
                    hcon.disconnect();
                    System.out.println("Result: "+result);;
                    if(result.startsWith("ï»¿ï»¿")) {
                        result = result.substring(6);
                    }
                    return result;

                } catch (ProtocolException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                progressDialog.dismiss();
                delegate.processFinish(o);
                System.out.println("Room Objects: "+o);
            }
        }
        ClsRoomList clst = new ClsRoomList(new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                result = (String) output;
                System.out.println("MainAct: "+result);
                GetRoomProcess getRoomProcess = new GetRoomProcess(result.trim());
                productList = getRoomProcess.getPlist();
                adapter = new RoomAdapter(getApplicationContext(),0, productList);
                recycler.setHasFixedSize(true);
                recycler.setAdapter(adapter);
            }
        });
        clst.execute();
    }
}
