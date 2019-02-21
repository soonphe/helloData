package com.soonphe.hadoop.base2.score;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author：soonphe
 * @Date：2019-02-20 14:07
 * @Description： 计算每个人三个月，每科总成绩
 * 原理：根据文件名判断科目，分别存入对应的字段中，最后累加
 */
public class ScoreDriver {
	
	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		//如果要以打jar包的方式，hadoop jar xxx.jar，需要设置这行代码
		// job.setJarByClass(ScoreDriver.class);
		job.setMapperClass(ScoreMapper.class);
		job.setReducerClass(ScoreReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Student.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Student.class);
		
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/score"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/score/result"));
		
		job.waitForCompletion(true);
	}

}
