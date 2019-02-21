package com.soonphe.hadoop.base1.max;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author：soonphe
 * @Date：2019-02-20 11:14
 * @Description： 最大值
 */
public class MaxDriver {

	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		
		job.setMapperClass(MaxMapper.class);
		job.setReducerClass(MaxReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/max"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/max/result"));
		
		job.waitForCompletion(true);
	}
}
