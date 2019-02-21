package com.soonphe.hadoop.base3.formatscore;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * @Author：soonphe
 * @Date：2019-02-21 10:37
 * @Description： 自定义格式输入 + 自定义格式输出 + 多输入源 + 多输出源
 * 要求输出结果：key为人名 value为学科成绩
 * tom math 90 english 80
 * rose math 85 english 75
 * 编码解析：一次读三行，第一行为key，第二行和第三行为value
 *
 * 自定义分区和多输出源区别：
 * 1.自定义分区在PatitionClass文件中编写，多输出源在Reducer文件中编写
 * 2.多输出源可以自定义文件名

 */
public class FormatScoreDriver {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

//		job.setJarByClass(FormatScoreDriver.class);
        job.setReducerClass(FormatScoreReducer.class);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //Hadoop多输入源，需要手动指定每个文件对应的path，inputFormat，mapper
        //此外，如果是多输入源，就不要设置job.setMapperClass();
        MultipleInputs.addInputPath(job, new Path("hdfs://192.168.31.242:9000/input/input.txt"),
                FormatScoreFormat.class, FormatScoreMapper.class);
        //MultipleInputs.addInputPath(job, new Path("hdfs://192.168.31.242:9000/input/part-r-00000"),
        //TextInputFormat.class,FormatScoreMapper2.class);

        //设置多输出源，注意名字要与reduce里的mos.write(名字)保持一致
        MultipleOutputs.addNamedOutput(job, "tom", TextOutputFormat.class, Text.class, Text.class);
        MultipleOutputs.addNamedOutput(job, "rose", TextOutputFormat.class, Text.class, Text.class);
        MultipleOutputs.addNamedOutput(job, "jary", TextOutputFormat.class, Text.class, Text.class);

        //设置自定义格式类，如果不设置，Hadoop默认用的TextInputFormat类中的lineRecordReader读取器
        //job.setInputFormatClass(FormatScoreFormat.class);

        //FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.31.242:9000/input"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.31.242:9000/input/result"));

        job.waitForCompletion(true);
    }
}
