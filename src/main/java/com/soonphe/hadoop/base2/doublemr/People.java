package com.soonphe.hadoop.base2.doublemr;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class People implements WritableComparable<People>{
	
	private String name;
	private int profit;

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeInt(profit);
		
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.name=in.readUTF();
		this.profit=in.readInt();
		
	}

	@Override
	public int compareTo(People o) {
	
		return this.profit-o.profit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "People [name=" + name + ", profit=" + profit + "]";
	}
	
	
}
