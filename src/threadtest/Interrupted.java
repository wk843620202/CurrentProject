package threadtest;

import java.util.concurrent.TimeUnit;

public class Interrupted {

	public static void main(String[] args) throws Exception {
		Thread sleepThread = new Thread(new SleepRunner(),"sleepRunner");
		sleepThread.setDaemon(true);
		
		Thread busyThread = new Thread(new SleepRunner(),"BusyRunner");
		busyThread.setDaemon(true);
		
		sleepThread.start();
		busyThread.start();
		
		TimeUnit.SECONDS.sleep(5);
		
	//	sleepThread.interrupt();
		busyThread.interrupt();
		
		System.out.println("sleepThread interrupted is " + sleepThread.isInterrupted());
		System.out.println("busyThread interrupted is " + busyThread.isInterrupted());
		
		TimeUnit.SECONDS.sleep(2);
		
		
	}
	
	static class SleepRunner implements Runnable{
		@Override
		public void run() {
			while (true) {
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	static class BusyRunner implements Runnable{
		@Override
		public void run() {
			while (true) {
				
			}
			
		}
	}

}
