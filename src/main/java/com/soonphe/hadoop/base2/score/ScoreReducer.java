package com.soonphe.hadoop.base2.score;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ScoreReducer extends Reducer<Text, Student, Text, Student>{
	
	@Override
	protected void reduce(Text key, Iterable<Student> values, Context context)
			throws IOException, InterruptedException {
	      Student tmp=new Student();
	      tmp.setName(key.toString());
	      
	      for(Student student:values){
	    	  tmp.setChinese(tmp.getChinese()+student.getChinese());
	    	  tmp.setEnglish(tmp.getChinese()+student.getEnglish());
	    	  tmp.setMath(tmp.getMath()+student.getMath());
	      }
	      context.write(key, tmp);
	}

}
