package com.soonphe.hadoop.base2.doublemr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ProfitMR2Driver {
	
	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
//		job.setJarByClass(ProfitMR2Driver.class);
		job.setMapperClass(ProfitMR2Mapper.class);
	
		
		job.setMapOutputKeyClass(People.class);
		job.setMapOutputValueClass(NullWritable.class);
		
	
		
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/doublemr/result"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/doublemr/result2"));
		
		job.waitForCompletion(true);
	}

}
