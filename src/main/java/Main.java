import bean.Task;
import consumer.Consumer;
import consumer.DefaultConsumerImpl;
import mq.Process;

import java.util.concurrent.TimeUnit;

public class Main {
	/**
	 * 如果不在一个vm
	 * 这一套需要开网络监听
	 * 还需要一个客户端发请求
	 *
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Process process = new Process();
		Consumer consumer1 = new DefaultConsumerImpl();
		Consumer consumer2 = new DefaultConsumerImpl();
		Consumer consumer3 = new DefaultConsumerImpl();
		process.addConsumer(consumer1);
		process.addConsumer(consumer2);
		process.addConsumer(consumer3);
		Task task1 = new Task("hello world 3",10);
		Task task2 = new Task("hello world 1",1);
		Task task3 = new Task("hello world 1",1);
		Task task4 = new Task("hello world 1",1);
		Task task5 = new Task("hello world 1",1);
//		Task task4 = new Task("hello world 1",1);
		process.addTask(task1);
		process.addTask(task2);
		process.addTask(task3);
		TimeUnit.SECONDS.sleep(2);
		process.addTask(task4);
		process.addTask(task5);
		process.addTask(task5);
		process.addTask(task5);
		TimeUnit.SECONDS.sleep(2);
		process.addTask(task5);
		process.addTask(task5);
		process.addTask(task5);
//		SynchronousQueue<Object> objects = new SynchronousQueue<>();
//		objects.offer(null);
//		process.addTask(task4);
	}
}
