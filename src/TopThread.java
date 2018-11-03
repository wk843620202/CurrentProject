import java.util.concurrent.TimeUnit;

public class TopThread {
	
	private static boolean stopRequested;
	
	private static synchronized void requestStop() {
		stopRequested = true;
	}
	
	private static synchronized boolean stopRequested() {
		return stopRequested;
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Thread backgroundThread = new Thread(new Runnable() {
			@Override
			public void run() {
				int i= 0;
				while (!stopRequested()) {
					i++;
					System.out.println(i);
				}
			}
		});
		backgroundThread.start();
		
		TimeUnit.SECONDS.sleep(1);
		
		requestStop();
		
	}

}
