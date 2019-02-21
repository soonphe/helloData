package com.soonphe.hadoop.base3.inputformat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AuthInputMapper extends Mapper<IntWritable, Text,IntWritable,Text>{
	
	@Override
	protected void map(IntWritable key, Text value, Mapper<IntWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		context.write(key, value);
	}

}
