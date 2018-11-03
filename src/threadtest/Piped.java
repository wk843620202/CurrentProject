package threadtest;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Piped {

	public static void main(String[] args) throws Exception {
		//PipedWriter 和  PipedReader，主要用于线程之间的数据传输
		// 而传输的媒介为内存
		PipedWriter out = new PipedWriter();
		
		PipedReader in = new PipedReader();
		
		out.connect(in);
		
		Thread printThread = new Thread(new Print(in),"PrintThread");
		
		printThread.start();
		
		int receive = 0;
		
		try {
			while ((receive = System.in.read()) != -1) {
				out.write(receive);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			out.close();
		}
	}
	
	static class Print implements Runnable{
		
		private PipedReader in;
		
		public Print(PipedReader in) {
			this.in = in;
		}
		
		@Override
		public void run() {
			int receive = 0;
			try {
				while ((receive = in.read()) != -1)  {
					System.out.print((char)receive);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}



