package com.soonphe.hadoop.base1.flow;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Author：soonphe
 * @Date：2019-02-20 11:15
 * @Description： 流量统计对象
 */
public class Flow implements Writable{
	private String phone;
	private String addr;
	private String name;
	private int flow;

	//write方法是序列化字段方法
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(phone);
		out.writeUTF(addr);
		out.writeUTF(name);
		out.writeInt(flow);
		
	}

	//readFields 是反序列化方法
	//注意，反序列化的顺序问题，要和write的序列顺序保持一致
	@Override
	public void readFields(DataInput in) throws IOException {
		this.phone=in.readUTF();
		this.addr=in.readUTF();
		this.name=in.readUTF();
		this.flow=in.readInt();
		
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	@Override
	public String toString() {
		return "Flow [phone=" + phone + ", addr=" + addr + ", name=" + name + ", flow=" + flow + "]";
	}
	
	
}
