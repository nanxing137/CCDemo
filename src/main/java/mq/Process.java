package mq;

import bean.Task;
import consumer.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 接受任务，分发任务
 */
public class Process{
	// 这里可以重写优先级队列
	PriorityQueue<TaskQueue> priorityQueue = new PriorityQueue<>();

	Queue<Task> queue = new LinkedBlockingDeque<>();

	// 暂时没啥用
	List<Handler> handlers = new ArrayList<>();

	public void process(){


	}

	/**
	 * 注册消费者
	 * @param consumer
	 */
	public void addConsumer(Consumer<Task> consumer){
		// 连一个消费者，自动加一个为此消费者增加的队列
		TaskQueue<Task> taskQueue = new TaskQueue<>();
		priorityQueue.add(taskQueue);
		Handler handler = new Handler(taskQueue,priorityQueue,consumer);
		// handler.start();

		handlers.add(handler);
	}


	/**
	 * 生产任务,将任务加到持有任务量最低的队列里
	 *
	 * 如果不在一个vm，这里改为从网络接收task
	 *
	 * 重写优先级队列后这里需要修改
	 * @param t
	 * @param <T>
	 */
	public<T extends Task> void addTask(T t){


//		queue.add(t);


		TaskQueue remove = priorityQueue.remove();
		synchronized (remove){
			remove.add(t);
			priorityQueue.add(remove);
			System.out.println();
			remove.notifyAll();
		}

	}
}
