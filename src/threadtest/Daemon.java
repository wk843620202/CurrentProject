package threadtest;

import java.util.concurrent.TimeUnit;

public class Daemon {

	public static void main(String[] args) {
		Thread thread = new Thread(new DaemonRunner(),"DaemonRunner");
		thread.setDaemon(true);
		thread.start();

	}
	
	static class DaemonRunner implements Runnable{
		@Override
		public void run() {
			
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				System.out.println("DaemonRunner finally run");
			}
		}
	}

}
