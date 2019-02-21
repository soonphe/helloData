package com.soonphe.hadoop.base2.doublemr;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author：soonphe
 * @Date：2019-02-20 14:50
 * @Description： 二级Mapper 实现序列化与compareTo排序
 */
public class ProfitMR2Mapper extends Mapper<LongWritable, Text, People, NullWritable>{
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
	
		String line=value.toString();

		//Hadoop默认的输出结果，key和value之间是以制表符分割的
		String[] data=line.split("	");
		People people=new People();
		people.setName(data[0]);
		people.setProfit(Integer.parseInt(data[1]));
		
		context.write(people, NullWritable.get());
	}

}
