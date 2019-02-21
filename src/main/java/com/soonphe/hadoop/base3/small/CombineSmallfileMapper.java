package com.soonphe.hadoop.base3.small;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CombineSmallfileMapper extends Mapper<LongWritable, BytesWritable, Text,Text>{
	private Text file=new Text();
		
	@Override
	protected void map(LongWritable key, BytesWritable value,
			Mapper<LongWritable, BytesWritable, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String fileName=context.getConfiguration().get("map.input.file.name");
		file.set(fileName);
		
			
		context.write(file, new Text(new String(value.getBytes()).trim()));
	}
}

