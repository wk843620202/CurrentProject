package threadtest;

import java.util.concurrent.TimeUnit;


public class ShutDown {
	
	

	public static void main(String[] args) throws Exception {
		Thread countThread = new Thread(new Runner(),"countThread");
		
		countThread.start();
		
		TimeUnit.SECONDS.sleep(1);
		
		countThread.interrupt();
		
		Runner two= new Runner();
		countThread = new Thread(two,"countThread");
		
		countThread.start();
		
		TimeUnit.SECONDS.sleep(1);
		
		two.cancel();

	}
	
	private static class Runner implements Runnable{
		
		private long  i = 0;
		
		private volatile boolean on = true;//一定要加volatile 关键字
		
		@Override
		public void run() {
			while (on && !Thread.currentThread().isInterrupted()) {
				i++;
			}
			System.out.println("count i = " + i);
			
		}
		
		public void cancel() {
			on = false;
		}
	}

}
