package threadtest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class DeprecatedMethod06 {
	
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) throws Exception {
		Thread printThread = new Thread(new PrintThread(),"printThread");
		printThread.setDaemon(true);
		printThread.start();
		
		TimeUnit.SECONDS.sleep(3);
		printThread.suspend();
		
		System.out.println("main thread suspend printThread at:" + 
				simpleDateFormat.format(new Date()));
		
		TimeUnit.SECONDS.sleep(3);
		
		printThread.resume();
		
		System.out.println("main thread resume printThread at:" + 
				simpleDateFormat.format(new Date()));
		
		TimeUnit.SECONDS.sleep(3);
		
		printThread.stop();
		
		System.out.println("main thread stop printThread at:" + 
				simpleDateFormat.format(new Date()));
		
		
		TimeUnit.SECONDS.sleep(3);

	}
	
	static class PrintThread implements Runnable{
		@Override
		public void run() {
			while (true) {
				System.out.println(Thread.currentThread().getName() +  "run at:" + 
						simpleDateFormat.format(new Date()));
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}

}
