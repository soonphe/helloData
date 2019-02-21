package com.soonphe.hadoop.base2.doublemr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author：soonphe
 * @Date：2019-02-20 14:49
 * @Description： 一级Mapper输出每个月人名，利润
 */
public class ProfitMR1Mapper extends Mapper<LongWritable,Text,Text,IntWritable>{
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line=value.toString();
		String[] data=line.split(" ");
		String name=data[1];
		int profit=Integer.parseInt(data[2])-Integer.parseInt(data[3]);
		context.write(new Text(name), new IntWritable(profit));
	}

}
