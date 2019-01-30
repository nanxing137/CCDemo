package mq;

import bean.Task;
import consumer.Consumer;

import java.util.PriorityQueue;


public class Handler {

	TaskQueue<Task> taskQueue;
	PriorityQueue<TaskQueue> priorityQueue;
	Consumer consumer;

	public Handler(TaskQueue<Task> taskQueue, PriorityQueue<TaskQueue> priorityQueue, Consumer consumer) {
		this.taskQueue = taskQueue;
		this.priorityQueue = priorityQueue;
		this.consumer = consumer;
		// 直接启动
		handler();
	}

	// 处理完需要调用hook调整优先级
	// 未实现
	public void handler() {
		while (true) {
			Task task = taskQueue.remove();
			synchronized (taskQueue) {
				while (null == task) {
					try {
						taskQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					task = taskQueue.remove();
				}
			}

			consumer.consume(task);
		}
	}


}
