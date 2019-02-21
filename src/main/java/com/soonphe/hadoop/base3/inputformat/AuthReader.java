package com.soonphe.hadoop.base3.inputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.LineReader;

import java.io.IOException;

/**
 * @Author：soonphe
 * @Date：2019-02-21 10:06
 * @Description： 格式读取器
 * RecordReader的两个泛型对应InputFormat类型的泛型类型
 */
public class AuthReader extends RecordReader<IntWritable, Text> {

    private FileSplit fs;
    private IntWritable key;
    private Text value;
    //注意导包，导的是hadoop.util包
    private LineReader reader;
    private int count;


    /*
     *
     */
    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        fs = (FileSplit) split;
        Path path = fs.getPath();
        Configuration conf = new Configuration();
        FileSystem system = path.getFileSystem(conf);
        FSDataInputStream in = system.open(path);
        reader = new LineReader(in);

    }

    /*
     *知识点1：nextKeyValue()这个方法会被调用多次，这个方法的返回值如果是true，就会被调用一次
     *直到返回false不被调用
     *知识点2：每当nextKeyValue()被调用一次，getCurrentKey和getCurrentValue也会被调用一次
     *知识点3:getCurrentKey()是给map传key的，getCurrentValue()是给map传value的
     *
     */
    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        key = new IntWritable();
        value = new Text();
        Text temp = new Text();
        //readLine是读取一行方法
        int lengh = reader.readLine(temp);
        //如果没有行可读，表示当前文件已处理完，所以返回false
        if (lengh == 0) {
            return false;
        } else {
            //相当于把每行内容赋值给value
            //value=temp;
            value.append(temp.getBytes(), 0, temp.getLength());
            count++;
            key.set(count);
            return true;
        }


    }

    @Override
    public IntWritable getCurrentKey() throws IOException, InterruptedException {
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
        if (reader != null) reader = null;

    }

}
