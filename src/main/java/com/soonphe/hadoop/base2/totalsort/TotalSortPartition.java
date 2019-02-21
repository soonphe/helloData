package com.soonphe.hadoop.base2.totalsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @Author：soonphe
 * @Date：2019-02-20 14:34
 * @Description： 自定义分区规制——按数值区间分区
 */
public class TotalSortPartition extends Partitioner<IntWritable, IntWritable>{

	@Override
	public int getPartition(IntWritable key, IntWritable value, int numPartitions) {
		String num=String.valueOf(key.get());
		if(num.matches("[0-9]")||num.matches("[0-9][0-9]")){
			return 0;
		}
		else if(num.matches("[0-9][0-9][0-9]")){
			return 1;
		}else{
			return 2;
		}
		
	}

}
