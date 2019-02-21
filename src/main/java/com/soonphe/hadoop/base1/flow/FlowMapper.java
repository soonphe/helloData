package com.soonphe.hadoop.base1.flow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable,Text,Text, Flow> {
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line=value.toString();
		Flow flow=new Flow();
		String[] data=line.split(" ");
		flow.setPhone(data[0]);
		flow.setAddr(data[1]);
		flow.setName(data[2]);
		flow.setFlow(Integer.parseInt(data[3]));
		// 按名称输出对象
		context.write(new Text(flow.getName()), flow);
		
	}

}
