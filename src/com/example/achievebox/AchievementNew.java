package com.example.achievebox;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.achievebox.R;

public class AchievementNew extends Activity implements OnClickListener {
	
	private ProgressDialog dialog;
	
	private Button AchievementAdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.achievement_new);

		AchievementAdd = (Button) findViewById(R.id.AchievementAdd);
        // When we creating a button and if we expect that to use for event handling we have to set the listener
		AchievementAdd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
			
		new RequestTask().execute("http://" + Constants.serverName + "/achievementAdd.php");
		
	}
	
	
	
	
	class RequestTask extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... params) {

			try {
                //������� ������ �� ������
                DefaultHttpClient hc = new DefaultHttpClient();
                ResponseHandler<String> res = new BasicResponseHandler();
                //�� � ��� ����� �������� post ������
                Log.w("MyApp", params[0]);
                HttpPost postMethod = new HttpPost(params[0]);
                //����� ���������� ��� ���������
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                //�������� ��������� �� ����� �����������
                //�����
                //nameValuePairs.add(new BasicNameValuePair("login", login.getText().toString()));
                //������
                //nameValuePairs.add(new BasicNameValuePair("pass", pass.getText().toString()));
                //�������� �� ������ � �������� �� ������
                postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //�������� ����� �� �������
                String response = hc.execute(postMethod, res);
                //�������� �� ������ ���������� ���������� ���������
                //Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //�� ��� ���� �� ����� ���������� � ���, putExtra(����, ���);
                //intent.putExtra(SecondActivity.JsonURL, response.toString());
                //startActivity(intent);
                
                Log.w("MyApp", response.toString());
                //Toast.makeText(AchievementNew.this, response.toString(), Toast.LENGTH_LONG).show();
                
	        } catch (Exception e) {
	                System.out.println("Exp=" + e);
	        }
	        return null;
	        
		}
		
		@Override
	    protected void onPostExecute(String result) {

	            dialog.dismiss();
	            super.onPostExecute(result);
	    }

	    @Override
	    protected void onPreExecute() {

	            dialog = new ProgressDialog(AchievementNew.this);
	            dialog.setMessage("Adding Achievement...");
	            dialog.setIndeterminate(true);
	            dialog.setCancelable(true);
	            dialog.show();
	            super.onPreExecute();
	    }
		
	}



	
	

}
