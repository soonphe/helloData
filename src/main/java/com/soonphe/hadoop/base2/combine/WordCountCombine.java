package com.soonphe.hadoop.base2.combine;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author：soonphe
 * @Date：2019-02-20 14:52
 * @Description： 自定义Combine
 * Hadoop默认没有combine——Mapper和Reduce交互，使每一个mapper中的数据合并发生在mapper中
 */
public class WordCountCombine extends Reducer<Text,IntWritable, Text, IntWritable>{

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
