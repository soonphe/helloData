package com.soonphe.hadoop.base2.totalsort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author：soonphe
 * @Date：2019-02-20 14:31
 * @Description： 全排序——单文件有序，总文件有序
 * 0~100 0分区
 * 100~1000 1分区
 * 1000以上 2分区
 * 每个文件中按照数字大小排序升序，统计每个数字出现的次数
 */
public class TotalSortDriver {
	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		
//		job.setJarByClass(TotalSortDriver.class);
		job.setMapperClass(TotalSortMapper.class);
		job.setReducerClass(TotalSortReducer.class);
		
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setNumReduceTasks(3);
		job.setPartitionerClass(TotalSortPartition.class);
		
	
		
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/totalsort"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/totalsort/result"));
		
		job.waitForCompletion(true);
	}

}
