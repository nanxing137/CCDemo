package consumer;

import bean.Task;
import mq.TaskQueue;

import java.util.concurrent.TimeUnit;

public class DefaultConsumerImpl<T extends Task> implements Consumer<T> {

	TaskQueue<T> taskQueue = new TaskQueue<>();

	/**
	 * 如果不在一个vm
	 * 这里改成发消息
	 * 需要接受客户端
	 * @param t
	 */
	@Override
	public void consume(T t) {
		new Thread(()->{
			System.out.print("当前线程为:" + Thread.currentThread() + ",获取到的当前任务工作量:" + t.getOneTaskCount());
			System.out.println("执行工作:"+t.getJob());
			try {
				// 模拟工作量
				TimeUnit.SECONDS.sleep(t.getOneTaskCount());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

}
