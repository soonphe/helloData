package com.soonphe.hadoop.base1.flow;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author：soonphe
 * @Date：2019-02-20 11:40
 * @Description： 流量统计——自定义序列化对象 + 自定义分区
 */
public class FlowDriver {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Flow.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Flow.class);

        // 设置Reducer分区数量，Hadoop默认就一个Reducer
        job.setNumReduceTasks(3);
        //设置自定义的分区类，如果不设置，默认用HashPartitoner
        //先分区=》再按key合并
        job.setPartitionerClass(FlowPartitioner.class);

        FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/flow"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/flow/result"));

        job.waitForCompletion(true);
    }
}
