import java.util.concurrent.TimeUnit;

public class TopThread3 {
	
	private static volatile boolean stopRequested;
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Thread backgroundThread = new Thread(new Runnable() {
			@Override
			public void run() {
				int i= 0;
				while (!stopRequested) {
					i++;
					System.out.println(i);
					
				}
			}
		});
		backgroundThread.start();
		
		TimeUnit.SECONDS.sleep(1);
		
		stopRequested = true;
		
	}

}
