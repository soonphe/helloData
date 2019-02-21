package com.soonphe.hadoop.base3.formatscore;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

/**
 * @Author：soonphe
 * @Date：2019-02-21 11:13
 * @Description： 根据人名将数据发送到三个不同结果文件中
 */
public class FormatScoreReducer extends Reducer<Text,Text,Text,Text>{

	private MultipleOutputs<Text,Text> mos;

	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		//业务需求：根据人名，将数据发送到三个不同结果文件中，比如tom=》tom.txt结果文件中
		//此外，如果用的是多输出源输出，就不用context进行输出了
		if(key.toString().equals("tom")){
			for(Text value:values){
				mos.write("tom", key, value);
			}
		}
		if(key.toString().equals("rose")){
			for(Text value:values){
				mos.write("rose", key, value);
			}
		}
		if(key.toString().equals("jary")){
			for(Text value:values){
				mos.write("jary", key, value);
			}
		}

	}
	@Override
	protected void setup(Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		mos=new MultipleOutputs<>(context);
	}


}
