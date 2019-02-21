package com.soonphe.hadoop.base3.formatscore;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author：soonphe
 * @Date：2019-02-21 11:06
 * @Description：
 * 这个mapper是用来处理part-0000文件的
 * 文件格式为：jary	 math 74 english 86
 * 所以直接用Hadoop默认的TextInputFormat
 * 人名 对应的学科
 */
public class FormatScoreMapper2 extends Mapper<LongWritable,Text,Text,Text>{
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String line=value.toString();
		//Hadoop默认key和value之间是以制表符分割的，所以这里以tap制表符分割
		String[] data=line.split("	");
		String name=data[0];
		String score=data[1];
		context.write(new Text(name), new Text(score));
	}

}
