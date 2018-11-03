import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TopThread4 {
	
	private static boolean stopRequested;
		
	
	
	private static synchronized void requestStop() {
		stopRequested = true;
	}
	
	private static synchronized boolean stopRequested() {
		return stopRequested;
	}
	
	private static volatile AtomicLong nextSerialNumber = new AtomicLong();
	
	public static long generateSerialNumber() {
		return nextSerialNumber.getAndIncrement();
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Thread backgroundThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!stopRequested()) {
					long i  = generateSerialNumber();
					System.out.println(i);
				}
			}
		});
		backgroundThread.start();
		
		TimeUnit.SECONDS.sleep(1);
		
		requestStop();
		
	}

}
