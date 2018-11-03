package threadtest;

public class Synchronized {

	public static void main(String[] args) {
		
		//对Synchronized class 对象加锁
		synchronized (Synchronized.class) {
			
		}
		//静态同步方法
		m();

	}
	
	public static synchronized void m() {}

}
