package com.soonphe.hadoop.base3.small;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author：soonphe
 * @Date：2019-02-21 11:10
 * @Description： 小文件合并
 * 三个文件 chinese.txt		math.txt	english.txt
 * 要求结果：三个文件合并成一个文件并输出
 */
public class CombineSmallfileDriver {
	
	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
	
		Job job =Job.getInstance(conf);
		job.setJarByClass(CombineSmallfileDriver.class);
		job.setMapperClass(CombineSmallfileMapper.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setInputFormatClass(CombineSmallfileInputFormat.class);

		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/wordcount"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/wordcount/result"));
		
		job.waitForCompletion(true);
	}

}

