package mq;

import bean.Task;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 每个任务队列
 *
 * @param <E>
 */
public class TaskQueue<E extends Task> extends LinkedList<E> implements Queue<E>, Comparable<TaskQueue<E>> {

	/**
	 * 维护的任务量
	 */
	private long taskCount;

	public long getTaskCount() {
		return taskCount;
	}

	//增加任务
	@Override
	public boolean add(E e) {
		taskCount += e.getOneTaskCount();
		return super.add(e);

	}

	//消费任务
	@Override
	public E remove() {
		E poll = super.poll();
		if (null != poll)
			taskCount -= poll.getOneTaskCount();
		return poll;
	}

	@Override
	public int compareTo(TaskQueue<E> o) {
		int compare = Long.compare(super.size(), o.size());
		return compare;
	}
}
