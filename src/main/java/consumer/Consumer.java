package consumer;

import bean.Task;
import mq.TaskQueue;

public interface Consumer<T extends Task> {

	void consume(T t);

}
