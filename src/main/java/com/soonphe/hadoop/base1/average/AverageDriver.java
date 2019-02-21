package com.soonphe.hadoop.base1.average;

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
 * @Description： 取平均值——mapper每行按空格分（人名和分数），reducer按人名key对分数求平均值
 */
public class AverageDriver {
	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		
		job.setMapperClass(AverageMapper.class);
		job.setReducerClass(AverageReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/average"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/average/result"));
		
		job.waitForCompletion(true);
	}

}
