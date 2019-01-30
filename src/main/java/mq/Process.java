package mq;

import bean.Task;
import consumer.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 接受任务，分发任务
 */
public class Process{

	// 总队列
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

		Handler handler = new Handler(queue,consumer);
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

		AtomicReference atomicReference = new AtomicReference();
		boolean b = atomicReference.compareAndSet(null, 1l);
		Object o = atomicReference.get();
		ConcurrentLinkedQueue<Object> objects = new ConcurrentLinkedQueue<>();
		objects.add(null);
//		queue.add(t);


		synchronized (queue){
			queue.offer(t);
			queue.notifyAll();
		}

	}
}
