package com.soonphe.hadoop.base3.output;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @Author：soonphe
 * @Date：2019-02-21 11:12
 * @Description： 格式化输出器
 */
public class AuthOutputWriter<K,V> extends RecordWriter<K, V>{
	private FSDataOutputStream out;
	private String keyValueSeparator;
	private String lineSeparator;
	

	public AuthOutputWriter(FSDataOutputStream out, String string, String string2) {
		this.out=out;
		this.keyValueSeparator=string;
		this.lineSeparator=string2;
	}
	
	/*
	 * ע�������˳������key kv�ָ��� value �зָ���
	 */
	@Override
	public void write(K key, V value) throws IOException, InterruptedException {
		out.write(key.toString().getBytes());
		out.write(keyValueSeparator.getBytes());
		out.write(value.toString().getBytes());
		out.write(lineSeparator.getBytes());
		
	}

	@Override
	public void close(TaskAttemptContext context) throws IOException, InterruptedException {
		if(out!=null)out=null;
		
	}

}
