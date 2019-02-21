package com.soonphe.hadoop.base2.combine;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	@Override
	//hello 1,1,1,1
	//hello 4
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
	
		int result=0;
		for(IntWritable value:values){
			System.out.println(value.get());
		}
		context.write(key, new IntWritable(result));
	}

}
