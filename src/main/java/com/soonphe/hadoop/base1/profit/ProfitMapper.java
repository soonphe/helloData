package com.soonphe.hadoop.base1.profit;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ProfitMapper extends Mapper<LongWritable, Text,Text,IntWritable> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line=value.toString();
		// 以空格切割
		String[] data=line.split(" ");
		String month=data[0];
		int profit=Integer.parseInt(data[1]);
		context.write(new Text(month), new IntWritable(profit));
	}
}
