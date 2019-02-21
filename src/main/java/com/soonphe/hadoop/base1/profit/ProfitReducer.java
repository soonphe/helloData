package com.soonphe.hadoop.base1.profit;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ProfitReducer extends Reducer<Text,IntWritable,Text,IntWritable>{

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		int result=0;
		for(IntWritable value:values){
			result=result+value.get();
		}
		context.write(key, new IntWritable(result));
	}
}
