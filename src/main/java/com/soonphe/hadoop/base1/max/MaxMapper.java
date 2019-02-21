package com.soonphe.hadoop.base1.max;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		 String line=value.toString();
		 String year=line.substring(8, 12);
		 int tempa=Integer.parseInt(line.substring(18));
		 
		 context.write(new Text(year),new IntWritable(tempa));
	}

}
