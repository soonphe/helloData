package com.soonphe.hadoop.base3.inputformat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * @Author：soonphe
 * @Date：2019-02-21 10:06
 * @Description： 格式化输入类
 * FileInputFormat  里的两个放心对应的输入的key和value类型，根据实际业务决定
 */
public class AuthInputFormat extends FileInputFormat<IntWritable, Text> {

    /*
     *Hadoop默认使用LineRecordReader，它把每行的行首偏移量和每行内容作为key,value传给map
     */
    @Override
    public RecordReader<IntWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context)
            throws IOException, InterruptedException {
        // TODO Auto-generated method stub
        return new AuthReader();
    }

}
