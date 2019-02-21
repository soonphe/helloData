package com.soonphe.hadoop.base1.flow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @Author：soonphe
 * @Date：2019-02-20 13:44
 * @Description： 自定义分区规则——按地区分区
 * 要想自定义分区，写一个类，集成Partitioner
 * 注意：第一个泛型类型，对应的是Mapper输出key类型
 * 第二个泛型类型，对应的是Mapper输出的value类型
 */
public class FlowPartitioner extends Partitioner<Text, Flow>{

	/*
	 *key 是Mapper输出key
	 *value 是Mapper输出的value
	 *numPartitions 分区数量是从job里拿到的numReduce数量
	 *
	 *注意分区的编号，比如如果是3个分区，编号是0~2
	 */
	@Override
	public int getPartition(Text key, Flow value, int numPartitions) {
		if(value.getAddr().equals("bj")){
			return 0;
		}if(value.getAddr().equals("sz")){
			return 1;
		}else{
			return 2;
		}
		
		
	}

}
