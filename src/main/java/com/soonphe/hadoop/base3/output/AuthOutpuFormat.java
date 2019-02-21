package com.soonphe.hadoop.base3.output;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Author：soonphe
 * @Date：2019-02-21 11:11
 * @Description： 自定义格式化输出类
 */
public class AuthOutpuFormat<K,V> extends FileOutputFormat<K, V>{

	@Override
	public RecordWriter<K, V> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
		//拿到输出结果路径
		Path path=super.getDefaultWorkFile(job,"");
		Configuration conf=new Configuration();
		FileSystem system=path.getFileSystem(conf);
		FSDataOutputStream out=system.create(path);
		//key和value之间的分隔符, 行之间的分隔符
		return new AuthOutputWriter<K,V>(out,"|","\r\n");
	}

}
