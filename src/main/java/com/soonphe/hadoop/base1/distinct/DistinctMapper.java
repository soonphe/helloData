package com.soonphe.hadoop.base1.distinct;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/*
 * LongWritable new LongWritable��2��
 * Text =��Text.toString()
 * IntWritable=��new IntWritalbe(2)|IntWritable.get()
 * NullWritable=>NullWritable.get()�õ�nullֵ
 */
public class DistinctMapper extends Mapper<LongWritable, Text,Text,NullWritable>{

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		context.write(value, NullWritable.get());
	}
}
