package cn.itcast.student;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class StudentService extends Service{
	private String[] names = {"张飞","李小龙","赵薇"};
	private IBinder binder = new StundentBinder();
	
	public String query(int no){
		
	/*	  try {
	   			//超过5s会报 ANR (“Application Not Responding”)错误
	   			Thread.sleep(20000);
	   		} catch (InterruptedException e) {
	   			e.printStackTrace();
	   		}
		  */
		if(no>0 && no<4){
	System.out.println(Thread.currentThread().getName());
			return names[no - 1];
		}
		return null;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		/*try {
			//超过5s会报 ANR (“Application Not Responding”)错误
			//同一个apk下面的service和Activity没有特殊的情况下，一般是在同一个进程的同一个线程里面的。
			//都在主UI线程，这就是为什么我们在service的onstart onbind中不能做延时较长的操作。 
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		return binder;
	}
	
	private class StundentBinder extends Binder implements IStundent{
		public String queryStudent(int no) {
			return query(no);
		}
	}

}
