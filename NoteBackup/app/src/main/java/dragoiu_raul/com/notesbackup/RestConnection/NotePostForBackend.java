package dragoiu_raul.com.notesbackup.RestConnection;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class NotePostForBackend extends AsyncTask<String, Void, String> {
    AsyncResponse delegate;

    public AsyncResponse getDelegate() {
        return delegate;
    }

    public void setDelegate(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... params) {

        StringBuilder data = new StringBuilder("");

        HttpURLConnection httpURLConnection = null;
        try {

            httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            httpURLConnection.setReadTimeout(3000);
            httpURLConnection.setConnectTimeout(3000);

            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            Log.d("debug---------------->", params[0]);
            Log.d("debug---------------->", params[1]);

            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(params[1]);
            writer.flush();
            writer.close();
            os.close();

            int statusCode = httpURLConnection.getResponseCode();
            if (statusCode == 200) {
                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);

                int inputStreamData = inputStreamReader.read();
                while (inputStreamData != -1) {
                    char current = (char) inputStreamData;
                    inputStreamData = inputStreamReader.read();
                    data.append(current);
                }
            } else {
                Log.e("TAG", "weird status code");
                data.append("error");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return data.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        delegate.processFinish(result);
    }
}
