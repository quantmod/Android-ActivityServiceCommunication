package cn.itcast.student;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText studentno;
    private ServiceConnection conn = new StudentServiceConnection();
    private IStundent iStundent;
    private TextView resultView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
           
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        resultView = (TextView) this.findViewById(R.id.resultView);
        
 /*       	View view = View.inflate(this, R.layout.main, null);
    	Button inflateButton = (Button) view.findViewById(R.id.button);*/
        
        studentno = (EditText) this.findViewById(R.id.studentno);
        Button button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(new ButtonClickListener());
        Intent service = new Intent(this, StudentService.class);
        bindService(service, conn, BIND_AUTO_CREATE);
        System.out.println(Thread.currentThread().getName());
    }
    
    private class StudentServiceConnection implements ServiceConnection{
		public void onServiceConnected(ComponentName name, IBinder service) {
			iStundent = (IStundent)service;
		}
		public void onServiceDisconnected(ComponentName name) {
			iStundent = null;
		}
    }
    
    @Override
	protected void onDestroy() {
		unbindService(conn);
		super.onDestroy();
	}

	private final class ButtonClickListener implements View.OnClickListener{
		public void onClick(View v) {
			
		/*	  try {
	   			//超过5s会报 ANR (“Application Not Responding”)错误
	   			Thread.sleep(20000);
	   		} catch (InterruptedException e) {
	   			e.printStackTrace();
	   		}
		  */	 
			
			System.out.println(Thread.currentThread().getName());
			String no = studentno.getText().toString();
			String name = iStundent.queryStudent(Integer.valueOf(no));
			resultView.setText(name);
		}
    }
}
