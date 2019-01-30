package bean;

import lombok.Data;

import java.util.Objects;

/**
 * 单个任务
 */
@Data
public class Task  {

	private long oneTaskCount;
	private Object job;

	public Task(Object job, long oneTaskCount) {
		super();
		this.job = job;
		this.oneTaskCount = oneTaskCount;
	}



}
