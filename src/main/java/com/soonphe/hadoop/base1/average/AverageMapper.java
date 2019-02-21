package com.soonphe.hadoop.base1.average;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AverageMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line=value.toString();
		String[] data=line.split(" ");
		String name=data[0];
		int score=Integer.parseInt(data[1]);
		context.write(new Text(name), new IntWritable(score));
	}

}
