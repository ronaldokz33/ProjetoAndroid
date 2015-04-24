package com.example.async;

        import android.app.AlertDialog;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.os.AsyncTask;

        import com.example.acessoaoservidor.R;

        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.UnsupportedEncodingException;
        import java.util.ArrayList;
        import java.util.List;


/**
 * Created by brunogoncalves on 4/4/14.
 */
public class LoginRequest extends AsyncTask<String, String, String> {

    //
    private String url = "http://107.170.204.35/public/index.php/api";// "http://192.168.0.101:90/Android/logar.php";
    private Context mContext;
    private ProgressDialog dialog;
    private AlertDialog alertDialog;

    private String user;
    private String password;

    public LoginRequest(Context c, String user, String password) {
        this.mContext = c;
        this.user = user;
        this.password = password;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(mContext).create();

        // Setting Dialog Title
        alertDialog.setTitle("Autenticação");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.ic_launcher);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
            }

        });


        dialog = new ProgressDialog(mContext);
        dialog.setMessage("Conectando ao Servidor ...");
        dialog.show();
    }

    @Override
    protected void onProgressUpdate(String... value) {
        dialog.setMessage(value[0]);
    }

    @Override
    protected String doInBackground(String... params) {
        //do your work here
        String result = "";

        // Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(url);

        // Building post parameters
        // key and value pair
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("usuario" ,user));
        nameValuePair.add(new BasicNameValuePair("senha", password));

        // Url Encoding the POST parameters
        try {

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        }

        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            result = getStringFromInputStream(response.getEntity().getContent());
            System.out.println("RESPOSTA: "+result);
            // writing response to log
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();

        }


        return result;
    }

    @Override
    protected void onPostExecute(String result) {

        dialog.dismiss();
        alertDialog.setMessage(result);
        alertDialog.show();

    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}
