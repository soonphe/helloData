package com.soonphe.hadoop.base2.doublemr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author：soonphe
 * @Date：2019-02-20 14:47
 * @Description： 多级MR——每个人三个月总利润 + 排序
 * 月份 人名 总收入 总支出
1 ls 2580 100
2 ls 2580 100
3 ls 2580 100
1 zs 2580 100
2 zs 2580 100
3 zs 2580 100
 */
public class ProfitMR1Driver {

	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
//		job.setJarByClass(ProfitMR1Driver.class);
		job.setMapperClass(ProfitMR1Mapper.class);
		job.setReducerClass(ProfitMR1Reducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/doublemr"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/doublemr/result"));
		
		job.waitForCompletion(true);
	}
}
