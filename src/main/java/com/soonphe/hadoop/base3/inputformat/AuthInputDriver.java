package com.soonphe.hadoop.base3.inputformat;

import com.soonphe.hadoop.base3.output.AuthOutpuFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author：soonphe
 * @Date：2019-02-21 10:01
 * @Description： 格式化输入
 * 得到行号与每行内容
 */
public class AuthInputDriver {
	
	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		
		job.setJarByClass(AuthInputDriver.class);
		job.setMapperClass(AuthInputMapper.class);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		//设置自定义格式类，如果不设置，Hadoop默认用的是TextInputFormat类中的lineRecordReader读取器
		job.setInputFormatClass(AuthInputFormat.class);
		job.setOutputFormatClass(AuthOutpuFormat.class);
		
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/input"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/input/result"));
		
		job.waitForCompletion(true);
	}

}
