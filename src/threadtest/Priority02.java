package threadtest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 线程优先级
 * @author wangkang
 *
 */
public class Priority02 {
	
	private static volatile boolean nostart = true;
	private static volatile boolean noEnd = true;

	public static void main(String[] args) throws Exception {
		List<Job> jobs = new ArrayList<Job>();
		
		for (int i = 0; i < 10; i++) {
			int priorit = i < 5? Thread.MIN_PRIORITY:Thread.MAX_PRIORITY;
			
			Job job = new Job(priorit);
			
			jobs.add(job);
			
			Thread thread = new Thread(job, "Thread: " + i);
			thread.setPriority(priorit);
			thread.start();
			
		}
		nostart = false;
		TimeUnit.SECONDS.sleep(10);
		
		for (Job job : jobs) {
			System.out.println("Job Priority:" + job.priority + ", count: " + job.jobCount);
		}

	}
	
	static class Job implements Runnable{
		
		private int priority;
		private long jobCount;
		
		public Job(int priority) {
			// TODO Auto-generated constructor stub
			this.priority = priority;
		}
		
		@Override
		public void run() {
			while (nostart) {
				Thread.yield();
			}
			while (noEnd) {
				Thread.yield();
				jobCount++;
			}
		}
	}

}
