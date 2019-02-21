package com.soonphe.hadoop.base1.word;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author：soonphe
 * @Date：2019-02-20 11:22
 * @Description： Reduce组件，接收Mapper组件的输出结果，并按照Mapper输出的key做合并
 * 第一个形参为Mapper输出的key类型
 * 第二个形参为Mapper输出的value类型
 * 第三个形参为自定义输出的key类型
 * 第四个形参为自定义输出的value类型
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    /**
     * @param key     Mapper输出的key
     * @param values  Mapper输出的value，注：这里的value为迭代器，按key聚合
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values,
                          Context context) throws IOException, InterruptedException {
        int result = 0;
        // 对数据迭代计算sum
        for (IntWritable value : values) {
            result = result + value.get();
        }
        context.write(key, new IntWritable(result));
    }
}
