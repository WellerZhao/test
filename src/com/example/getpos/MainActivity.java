package com.example.getpos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView mStudentTextView;
	String response;
	TextView mClassTextView;
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			mStudentTextView.setText(mStudentTextView.getText() + msg.obj.toString() + "\n");
    		System.out.println(mStudentTextView.getText());
		}
		
	};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setupViews();
	}

	private void setupViews(){  
        mStudentTextView = (TextView)findViewById(R.id.mStudentTextView);  
        mClassTextView = (TextView)findViewById(R.id.mClassTextView);  
        System.out.println("cao");
        try {  
            //获取后台返回的Json对象 
        	new Thread (){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("nima");
		            String url = "http://192.168.1.100:8080/Form/Android/get_menu";
		            HttpClient httpClient = new DefaultHttpClient();
		            HttpGet get = new HttpGet(url);
		            
		            HttpResponse httpResponse;
					try {
						httpResponse = httpClient.execute(get);
						HttpEntity entity = httpResponse.getEntity();
			            if (entity != null){
			            	BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
			            	String line = null;
			            	System.out.println("Fuck");
			            	while ((line = br.readLine()) != null){
			            		Message msg = new Message ();
			            		msg.what = 0x123;
			            		msg.obj = line;
			            		handler.sendMessage(msg);
			            		
			            	}
			            }
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
				}
        		
        	}.start();
        	
            
//            System.out.println("fuck");
//            System.out.println("fuck" + mJsonObject.toString());
//            //获得学生数组  
//            String mJsonArray = mJsonObject.getString("academy");  
//            //获取第一个学生  
//            String firstStudent = mJsonObject.getString("birthDay"); 
//            //获取班级  
//            String classes = mJsonObject.getString("email");  
//              
//            String studentInfo = mJsonArray + "\t" + firstStudent + "\t" + classes;
//            System.out.println(studentInfo);
//              
//            mStudentTextView.setText(studentInfo);  
//              
//            mClassTextView.setText("班级: " + classes);  
//        } catch (JSONException e) {  
//            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
