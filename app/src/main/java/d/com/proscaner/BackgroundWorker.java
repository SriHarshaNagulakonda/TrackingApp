package d.com.proscaner;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.support.v4.content.ContextCompat.getSystemService;


public class BackgroundWorker extends AsyncTask<String,Void,String> {
    AuthActivity context;
    AlertDialog alertDialog;


    BackgroundWorker (AuthActivity ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "https://jayakrishna123.000webhostapp.com/eastcoast.php";
        String register_url ="https://jayakrishna123.000webhostapp.com/CMMS/Checker.php";
        String update_url ="https://jayakrishna123.000webhostapp.com/CMMS/update.php";


        if(type.equals("login")) {
            try {
                String user_name =params[1];
                String password =params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {

                    result += line;
                }
                String a="loginsuccess";

                context.getMessage(result, result.toLowerCase().equals(a));
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (Exception e) {

                context.getMessage("Not Subbmitted", false);
                e.printStackTrace();
            }
        }

        else if (type.equals("Check")){

            try {
                String user_name =params[1];
                //String password =params[2];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")
                        ;
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {

                    result += line;
                }
    Boolean a;
                if(result.equals("success"))
                {
                    a=true;
                }
                else{
                    a=false;
                }
               // result="done";
               // line="done";
                context.getMessage(result,a);// result.toLowerCase().equals(a));
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (Exception e) {

                context.getMessage("Not Subbmitted", false);
                e.printStackTrace();
            }
        }
        else  if(type.equals("update")){
            try {
//                String user_name =params[1];
//                String password =params[2];
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                Date dateobj = new Date();
                //array[6]=df.format(dateobj);

                URL url = new URL(update_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(params[11],"UTF-8")+"&"
//                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
            String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(params[1],"UTF-8")+"&"+
                    URLEncoder.encode("scan","UTF-8")+"="+URLEncoder.encode(params[2],"UTF-8")+"&"+
                    URLEncoder.encode("model","UTF-8")+"="+URLEncoder.encode(params[3],"UTF-8")+"&"+
                    URLEncoder.encode("specifications","UTF-8")+"="+URLEncoder.encode(params[4],"UTF-8")+"&"+
                    URLEncoder.encode("codallife","UTF-8")+"="+URLEncoder.encode(params[5],"UTF-8")+"&"+
                    URLEncoder.encode("procurement","UTF-8")+"="+URLEncoder.encode(params[6],"UTF-8")+"&"+
                    URLEncoder.encode("dateofisuse","UTF-8")+"="+URLEncoder.encode(params[7],"UTF-8")+"&"+
                    URLEncoder.encode("destination","UTF-8")+"="+URLEncoder.encode(params[8],"UTF-8")+"&"+
                    URLEncoder.encode("warenty","UTF-8")+"="+URLEncoder.encode(params[9],"UTF-8")+"&"+
                    URLEncoder.encode("amcperiod","UTF-8")+"="+URLEncoder.encode(params[10],"UTF-8")+"&"+
                    URLEncoder.encode("text","UTF-8")+"="+URLEncoder.encode(params[11],"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedReader.readLine())!= null) {

                result += line;
            }
            String a="!!!Submitted!!!";

            context.getMessage(result, result.toLowerCase().equals(a));
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;
        } catch (Exception e) {

            context.getMessage("Not submitted", false);
            e.printStackTrace();
        }
        }
//        else if (type.equals("forgetpassword")){
//
//            try {
//                String str_email =params[1];
//
//                URL url = new URL(forget_url);
//                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
//                httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setDoOutput(true);
//                httpURLConnection.setDoInput(true);
//                OutputStream outputStream = httpURLConnection.getOutputStream();
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(str_email,"UTF-8");
//                bufferedWriter.write(post_data);
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
//                String result="";
//                String line="";
//                while((line = bufferedReader.readLine())!= null) {
//                    result += line;
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                return result;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return null;
    }

    private Object getSystemService(String connectivityService) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");

    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
        if (result=="Loginsucess"){

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
