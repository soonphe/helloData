package com.soonphe.hadoop.base1.word;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * @Author：soonphe
 * @Date：2019-02-19 11:14
 * @Description： 单词统计
 * 知识点：
 * 1.reducer会接收mapper的输出结果，并且按照mapper输出的key做合并，并封装到Iterable通过reduce函数传过来
 * 2.引入reduce组件后，输出结果是reducer的输出结果
 * 3.可以只有mapper组件，但是不是只有Reducer组件
 */
public class WordCountDriver {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);    //job代表MR的一个job任务

        //设置Mapper组件和Reducer组件
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //设置Mapper输出的key，value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置job输出的key，value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //指定处理的文件路径，可指定到目录或单文件级别
        FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/word"));
        //指定输出结果的目录，会自动生成结果文件（如:_SUCCESS,	part-r-00000）
        FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/word/result"));

        job.waitForCompletion(true);


    }

}
