package com.example.iot;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by arpitkh996 on 12-03-2016.
 */
public class HttpRequestHandler extends AsyncTask<String,String,String> {
    String address;
    URL url;
    Context context;
    AsyncInterface s;
    String errors=null;
    String isPost;
    boolean encode;
    public HttpRequestHandler(String address,String isPost,boolean encode, Context context) {
        this.address = address;
        this.context=context;
        this.isPost=isPost;
        this.encode=encode;
        s=(AsyncInterface)context;
        try {
            url=new URL(address);
        } catch (MalformedURLException e) {
            Toast.makeText(context,"MalformedURLException",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    @Override
    public void onProgressUpdate(String... da){
        Toast.makeText(context,da[0],Toast.LENGTH_SHORT).show();
    }
    @Override
    protected String doInBackground(String... data) {
        String response = "";
        try {

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod(isPost);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Type", "application/json");
            DataIncomingHolder dataIncomingHolder=new DataIncomingHolder(data[0],4,1);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(dataIncomingHolder.toString());
            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";

            }
    //        publishProgress(response);
        } catch (Exception e) {
            errors=e.getMessage();
            e.printStackTrace();
//            if(s!=null)s.setText("Error \n"+e.getCause().toString());
        }
        return response;
    }
    @Override
    public void onPostExecute(String a){
        super.onPostExecute(a);
        Toast.makeText(context,"Done"+"\n"+"Errors "+errors,Toast.LENGTH_LONG).show();
        if(s!=null)s.setText(a);
    }

    String encode(String sa){
        try {
            return URLEncoder.encode(sa,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
