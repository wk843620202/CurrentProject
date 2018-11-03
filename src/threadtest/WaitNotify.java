package threadtest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
	
	static boolean flag = true;
	static Object lock = new Object();

	public static void main(String[] args) throws Exception {
		Thread waitThread = new Thread(new Wait(),"WaitThread");
		waitThread.start();
		
		TimeUnit.SECONDS.sleep(1);
		
		Thread notifyThread = new Thread(new Notify(),"notifyThread");
		notifyThread.start();
		
	}
	
	static class Wait implements Runnable{
		@Override
		public void run() {
			synchronized (lock) {
				//加锁，拥有lock 的monitor
				while (flag) {
					// 当条件不满足时，继续wait， 同时释放lock的锁
					try {
						System.out.println(Thread.currentThread() + 
								" flag is true.wait @ "+ new SimpleDateFormat("HH:mm:ss")
								.format(new Date()));
						
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// 条件满足，完成工作
				System.out.println(Thread.currentThread() + 
						"flag is false . running @ "+ new SimpleDateFormat("HH:mm:ss")
						.format(new Date()));
			}
		}
	}
	
	static class Notify implements Runnable{
		@Override
		public void run() {
			//加锁，拥有lock 的monitor
			synchronized (lock) {
				//获取lock的锁，然后进行通知，通知时不会释放lock的锁
				//直到当前线程释放了lock后，waitThread 才能从wait方法中返回
				System.out.println(Thread.currentThread() + 
						" hold lock notify @ "+ new SimpleDateFormat("HH:mm:ss")
						.format(new Date()));
				
				lock.notifyAll();
				flag = false;
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//再次加锁
			System.out.println(Thread.currentThread() + 
					" hold lock again @ "+ new SimpleDateFormat("HH:mm:ss")
					.format(new Date()));
		}
	}

}
