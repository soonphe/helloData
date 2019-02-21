package com.soonphe.hadoop.base1.flow;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FlowReducer extends Reducer<Text, Flow,Text, Flow>{
	
	@Override 
	protected void reduce(Text key, Iterable<Flow> values, Context context)
			throws IOException, InterruptedException {
		Flow tmp=new Flow();
		for(Flow flow:values){
			tmp.setFlow(tmp.getFlow()+flow.getFlow());
			tmp.setPhone(flow.getPhone());
			tmp.setAddr(flow.getAddr());
			tmp.setName(flow.getName());
		}
		
		context.write(key, tmp);
	}

}
