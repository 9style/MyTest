package cn;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutor extends Thread {
	String name;

	public MyExecutor(String s) throws MalformedURLException, RemoteException,
			NotBoundException {
		this.name = s;
	}

	public void run() {
		try {
			System.out.println(Thread.currentThread().getName());
			System.out.println(" start....");
			System.out.println(name);
			System.out.println("result:" + name);
			Thread.sleep((int) (Math.random() * 1000));
			System.out.println(" end...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws MalformedURLException,
			RemoteException, NotBoundException {
		ExecutorService service = Executors.newFixedThreadPool(4);

		Queue<String> allTasks = new ConcurrentLinkedQueue<String>();
		allTasks.offer("1111");
		allTasks.offer("2222");
		allTasks.offer("3333");
		allTasks.offer("4444");
		allTasks.offer("5555");
		allTasks.offer("6666");

		String str;

		while ((str = allTasks.poll()) != null) {
			service.execute(new MyExecutor(str));
			System.out.println(str);
		}

		System.out.println("submit finish");
		service.shutdown();
	}
}