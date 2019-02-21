package com.soonphe.hadoop.base2.doublemr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author：soonphe
 * @Date：2019-02-20 14:49
 * @Description： 一级Reducer合并每个人三个月总利润
 */
public class ProfitMR1Reducer extends Reducer<Text, IntWritable,Text,IntWritable>{
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
