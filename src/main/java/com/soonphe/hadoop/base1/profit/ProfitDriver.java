package com.soonphe.hadoop.base1.profit;

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
 * @Description： 自定义分区
 */
public class ProfitDriver {
	
	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		
		job.setMapperClass(ProfitMapper.class);
		job.setReducerClass(ProfitReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		//设置分区数量（reduce任务数量），如果不设置,默认是1个
		//hadoop的默认分区策略为hash散列算法：key.hashCode()&integer.max_value%2 ——分区数量
		job.setNumReduceTasks(3);
		
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/profit"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/profit/result"));
		
		job.waitForCompletion(true);
	}

}
