package com.soonphe.hadoop.base3.formatscore;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.LineReader;

import java.io.IOException;

public class FormatScoreReader extends RecordReader<Text, Text>{
	
	private FileSplit fs;
	private Text key;
	private Text value;
	private LineReader reader;

	@Override
	public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
		fs=(FileSplit) split;
		Path path=fs.getPath();
		Configuration conf=new Configuration();
		FileSystem system=path.getFileSystem(conf);
		FSDataInputStream in=system.open(path);
		reader=new LineReader(in);
		
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		key=new Text();
		value=new Text();
		Text temp=new Text();
		//这里读取第一行作为人名
		int lengh=reader.readLine(temp);
		if(lengh==0){
			return false;
		}else{
			//注意，对于赋值操作，不要直接调用key=temp;相当于值的拼接
			key.set(temp);
			//连续读两行，获取学生所有成绩
			for(int i=0;i<2;i++){
				reader.readLine(temp);
				//tom math 90 english 80
				//追加字符串
				value.append(temp.getBytes(), 0,temp.getLength());
				//追加空格
				value.append(" ".getBytes(),0," ".length());
			}
			return true;
		}
		
	}

	@Override
	public Text getCurrentKey() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public Text getCurrentValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void close() throws IOException {
		if(reader!=null)reader=null;
		
	}

}
