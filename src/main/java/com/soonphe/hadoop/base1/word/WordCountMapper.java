package com.soonphe.hadoop.base1.word;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author：soonphe
 * @Date：2019-02-20 11:19
 * @Description： Map组件
 * 第一个形参为Mapper输入的key类型，这个类型一般固定，LongWritable
 * 第二个形参为Mapper输入的value类型，对应的文件，Text
 * 第三个形参为Mapper输出的key类型，这个类型用户自定义，LongWritable
 * 第四个形参为Mapper输出的value类型，这个类型用户自定义，LongWritable
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    /**
     * map方法，传递每行的行首偏移量和内容
     *
     * @param key     行首偏移量
     * @param value   每行内容
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        //以空格切割
        String[] words = line.split(" ");
        for (String word : words) {
            // 打印key和value——
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
