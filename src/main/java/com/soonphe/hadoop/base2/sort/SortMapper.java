package com.soonphe.hadoop.base2.sort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SortMapper extends Mapper<LongWritable, Text, Movie, NullWritable>{

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line=value.toString();
		String[] data=line.split(" ");
		Movie movie=new Movie();
		movie.setName(data[0]);
		movie.setHot(Integer.parseInt(data[1]));
		context.write(movie, NullWritable.get());
	}
}
