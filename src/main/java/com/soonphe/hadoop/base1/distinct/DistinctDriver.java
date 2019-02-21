package com.soonphe.hadoop.base1.distinct;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * @Author：soonphe
 * @Date：2019-02-20 11:36
 * @Description： 数据去重——reducer打印key和null
 */
public class DistinctDriver {
	
	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		
		job.setMapperClass(DistinctMapper.class);
		job.setReducerClass(DistinctReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/distinct"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/distinct/result"));
		
		job.waitForCompletion(true);
	}

}
