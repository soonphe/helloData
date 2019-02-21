package com.soonphe.hadoop.base2.sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author：soonphe
 * @Date：2019-02-20 14:25
 * @Description： 排序——MR会对Mapper输出的Key自动排序
 */
public class SortDriver {

	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		
//		job.setJarByClass(SortDriver.class);
		job.setMapperClass(SortMapper.class);
		
		
		job.setMapOutputKeyClass(Movie.class);
		job.setMapOutputValueClass(NullWritable.class);
		
	
		
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/sort"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/sort/result"));
		
		job.waitForCompletion(true);
	}
}
