package com.soonphe.hadoop.base2.score;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class ScoreMapper extends Mapper<LongWritable, Text, Text, Student>{
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line=value.toString();
		String[] data=line.split(" ");
		String name=data[1];
		int score=Integer.parseInt(data[2]);
		Student student=new Student();
		student.setName(name);
		FileSplit split=(FileSplit) context.getInputSplit();
		//split.getPath()得到的是文件名，此外需要再调用getName()来获取文件名
		if(split.getPath().getName().equals("chinese.txt")){
			student.setChinese(score);
		}
		else if(split.getPath().getName().equals("english.txt")){
			student.setEnglish(score);
		}else {
			student.setMath(score);
		}
		context.write(new Text(student.getName()), student);
	}


}
