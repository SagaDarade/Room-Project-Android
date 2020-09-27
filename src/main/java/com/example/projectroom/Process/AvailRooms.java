package com.example.projectroom.Process;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.projectroom.Domain.MainDomain;
import com.example.projectroom.Interfare.SubStrConvert;

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

public class AvailRooms extends AsyncTask {

    List<RoomPOJO> plist;
    String result;
    public AsyncResponse deleg;

    public AvailRooms(AsyncResponse deleg) {
        this.deleg = deleg;
        plist = new ArrayList<>();
    }

//    private static String subStr(String str){
//        String s = str.substring((str.indexOf("\\[")),str.length());
//        return s;
//    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        deleg.processFinish(o);
        System.out.println("Room Objects: "+o);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        String myurl= MainDomain.getDomain()+"viewAvailRooms.php";
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
            System.out.println("Result: "+result);
            if(result.startsWith("ï»¿ï»¿")) {
                result = result.substring(6);
            }
//            result = subStr(result);
            System.out.println(result);
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
}
