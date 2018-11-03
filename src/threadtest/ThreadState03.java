package threadtest;

import java.util.concurrent.TimeUnit;

/**
 * 线程状态
 * @author wangkang
 *
 */
public class ThreadState03 {

	public static void main(String[] args) {
		new Thread(new TimeWaiting(),"TimeWaitingThread").start();
		new Thread(new Waiting(),"waitingThread").start();
		new Thread(new Block(),"BlockThread-1").start();
		new Thread(new Block(),"BlockThread-2").start();

	}
	
	//该线程不断进行休眠
	static class TimeWaiting implements Runnable{
		@Override
		public void run() {
			while (true) {
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//该线程在Waiting.class实例上等待
	static class Waiting implements Runnable{
		@Override
		public void run() {
			while (true) {
				synchronized (Waiting.class) {
					try {
						Waiting.class.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}		
	}
	//该线程在Block.class 实例上加锁后，不会释放该锁
	static class Block implements Runnable{
		@Override
		public void run() {
			synchronized (Block.class) {
				while (true) {
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}		
	}

}
