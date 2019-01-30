package mq;

import bean.Task;
import consumer.Consumer;

import java.util.PriorityQueue;
import java.util.Queue;


public class Handler {

	Queue<Task> queue;
	Consumer consumer;

	public Handler(Queue<Task> queue, Consumer consumer) {
		this.queue = queue;
		this.consumer = consumer;
		// 直接启动
		handler();
	}

	// 处理完需要调用hook调整优先级
	// 未实现
	public void handler() {
		while (true) {
			Task task = queue.poll();
			if (null==task) {
				synchronized (queue) {
					while (null == task) {
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						task = queue.poll();
					}
				}
			}

			consumer.consume(task);
		}
	}


}
