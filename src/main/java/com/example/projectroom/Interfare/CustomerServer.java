package com.example.projectroom.Interfare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.projectroom.Domain.MainDomain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class CustomerServer extends AsyncTask {

    Context c;
    ProgressDialog pd;
    AlertDialog alert;

    public CustomerServer(Context c) {
        this.c = c;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("SAVING");
        pd.setMessage("Please wait a moment...!");
        pd.show();
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        String r = (String)o;
        pd.dismiss();
        Toast.makeText(c,r,Toast.LENGTH_SHORT).show();

        alert = new AlertDialog.Builder(c).create();
        alert.setTitle("- Task Res3ult -");
        alert.setMessage(r);
        alert.show();
    }

    @Override
    protected Object doInBackground(Object[] obj) {
        String type = (String) obj[0];

        if(type.equals("addCust")){
            try{
                String name = (String) obj[1];
                String addrs = (String) obj[2];
                String Bdate = (String) obj[3];
                String Ph1 = (String) obj[4];
                String Ph2 = (String) obj[5];
                String Email = (String) obj[6];
                String Adhaar = (String) obj[7];
                String PAN = (String) obj[8];
                String JoinDate = (String) obj[9];
                String LeaveDate = (String) obj[10];
                String Meter = (String) obj[11];
                String RoomNum = (String) obj[12];
                String addUrl = MainDomain.getDomain()+"addCustomer.php";
                URL url = new URL(addUrl);
                HttpURLConnection hcon = (HttpURLConnection) url.openConnection();
                hcon.setDoOutput(true);
                hcon.setDoInput(true);
                hcon.setRequestMethod("POST");

                OutputStream out = hcon.getOutputStream();
                BufferedWriter bw = new BufferedWriter(
                        new OutputStreamWriter(out,"UTF-8"));

                String Post_data = URLEncoder.encode("name","UTF-8")+"="+
                                    URLEncoder.encode(name,"UTF-8")+"&"+
                            URLEncoder.encode("addr","UTF-8")+"="+
                                URLEncoder.encode(addrs,"UTF-8")+"&"+
                            URLEncoder.encode("bdate","UTF-8")+"="+
                                URLEncoder.encode(Bdate,"UTF-8")+"&"+
                            URLEncoder.encode("ph1","UTF-8")+"="+
                                URLEncoder.encode(Ph1,"UTF-8")+"&"+
                            URLEncoder.encode("ph2","UTF-8")+"="+
                                URLEncoder.encode(Ph2,"UTF-8")+"&"+
                            URLEncoder.encode("email","UTF-8")+"="+
                                URLEncoder.encode(Email,"UTF-8")+"&"+
                            URLEncoder.encode("adhar","UTF-8")+"="+
                                URLEncoder.encode(Adhaar,"UTF-8")+"&"+
                            URLEncoder.encode("pan","UTF-8")+"="+
                                URLEncoder.encode(PAN,"UTF-8")+"&"+
                            URLEncoder.encode("jDate","UTF-8")+"="+
                                URLEncoder.encode(JoinDate,"UTF-8")+"&"+
                            URLEncoder.encode("Ldate","UTF-8")+"="+
                                URLEncoder.encode(LeaveDate,"UTF-8")+"&"+
                            URLEncoder.encode("mtr","UTF-8")+"="+
                                URLEncoder.encode(Meter,"UTF-8")+"&"+
                            URLEncoder.encode("rNum","UTF-8")+"="+
                                URLEncoder.encode(RoomNum,"UTF-8");

                bw.write(Post_data);
                bw.flush();
                bw.close();
                out.close();

                InputStream instream = hcon.getInputStream();
                BufferedReader bufRead = new BufferedReader(new InputStreamReader
                        (instream,"iso-8859-1"));

                String result="", line="";
                while((line = bufRead.readLine()) != null) {
                    result += line;
                }
                bufRead.close();
                instream.close();
                hcon.disconnect();
                if(result.startsWith("ï»¿ï»¿")) {
                    result = result.substring(6);
                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(type.equals("updtCust")){
            try{
                String name = (String) obj[1];
                String addrs = (String) obj[2];
                String Bdate = (String) obj[3];
                String Ph1 = (String) obj[4];
                String Ph2 = (String) obj[5];
                String Email = (String) obj[6];
                String Adhaar = (String) obj[7];
                String PAN = (String) obj[8];
                String JoinDate = (String) obj[9];
                String LeaveDate = (String) obj[10];
                String Meter = (String) obj[11];
                String RoomNum = (String) obj[12];
                String CID = (String) obj[13];

                String addUrl = MainDomain.getDomain()+"updtCust.php";
                URL url = new URL(addUrl);
                HttpURLConnection hcon = (HttpURLConnection) url.openConnection();
                hcon.setDoOutput(true);
                hcon.setDoInput(true);
                hcon.setRequestMethod("POST");

                OutputStream out = hcon.getOutputStream();
                BufferedWriter bw = new BufferedWriter(
                        new OutputStreamWriter(out,"UTF-8"));

                String Post_data = URLEncoder.encode("name","UTF-8")+"="+
                        URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("addr","UTF-8")+"="+
                        URLEncoder.encode(addrs,"UTF-8")+"&"+
                        URLEncoder.encode("bdate","UTF-8")+"="+
                        URLEncoder.encode(Bdate,"UTF-8")+"&"+
                        URLEncoder.encode("ph1","UTF-8")+"="+
                        URLEncoder.encode(Ph1,"UTF-8")+"&"+
                        URLEncoder.encode("ph2","UTF-8")+"="+
                        URLEncoder.encode(Ph2,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+
                        URLEncoder.encode(Email,"UTF-8")+"&"+
                        URLEncoder.encode("adhar","UTF-8")+"="+
                        URLEncoder.encode(Adhaar,"UTF-8")+"&"+
                        URLEncoder.encode("pan","UTF-8")+"="+
                        URLEncoder.encode(PAN,"UTF-8")+"&"+
                        URLEncoder.encode("jDate","UTF-8")+"="+
                        URLEncoder.encode(JoinDate,"UTF-8")+"&"+
                        URLEncoder.encode("Ldate","UTF-8")+"="+
                        URLEncoder.encode(LeaveDate,"UTF-8")+"&"+
                        URLEncoder.encode("mtr","UTF-8")+"="+
                        URLEncoder.encode(Meter,"UTF-8")+"&"+
                        URLEncoder.encode("rNum","UTF-8")+"="+
                        URLEncoder.encode(RoomNum,"UTF-8")+"&"+
                        URLEncoder.encode("cid","UTF-8")+"="+
                        URLEncoder.encode(CID,"UTF-8");

                bw.write(Post_data);
                bw.flush();
                bw.close();
                out.close();

                InputStream instream = hcon.getInputStream();
                BufferedReader bufRead = new BufferedReader(new InputStreamReader
                        (instream,"iso-8859-1"));

                String result="", line="";
                while((line = bufRead.readLine()) != null) {
                    result += line;
                }
                bufRead.close();
                instream.close();
                hcon.disconnect();
                if(result.startsWith("ï»¿")) {
                    result = result.substring(3);
                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(type.equals("delCust")){
            try{
                String RoomNum = (String) obj[1];
                String CID = (String) obj[2];

                String addUrl = MainDomain.getDomain()+"delCust.php";
                URL url = new URL(addUrl);
                HttpURLConnection hcon = (HttpURLConnection) url.openConnection();
                hcon.setDoOutput(true);
                hcon.setDoInput(true);
                hcon.setRequestMethod("POST");

                OutputStream out = hcon.getOutputStream();
                BufferedWriter bw = new BufferedWriter(
                        new OutputStreamWriter(out,"UTF-8"));

                String Post_data = URLEncoder.encode("rNum","UTF-8")+"="+
                        URLEncoder.encode(RoomNum,"UTF-8")+"&"+
                        URLEncoder.encode("cid","UTF-8")+"="+
                        URLEncoder.encode(CID,"UTF-8");

                bw.write(Post_data);
                bw.flush();
                bw.close();
                out.close();

                InputStream instream = hcon.getInputStream();
                BufferedReader bufRead = new BufferedReader(new InputStreamReader
                        (instream,"iso-8859-1"));

                String result="", line="";
                while((line = bufRead.readLine()) != null) {
                    result += line;
                }
                bufRead.close();
                instream.close();
                hcon.disconnect();
                if(result.startsWith("ï»¿")) {
                    result = result.substring(3);
                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
