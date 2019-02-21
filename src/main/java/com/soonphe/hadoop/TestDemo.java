package com.soonphe.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.net.URI;

public class TestDemo {
	/*
	 * 测试连接和从HDFS上下载文件的方法
	 */
	@Test
	public void testConnectAndGet() throws Exception{
		//Configuration这个类是Hadoop环境变量类
		Configuration conf=new Configuration();
		//conf.set("dfs.replication","1");
		//FileSystem 这个抽象类表示的是Hadoop的文件系统。HDFS只是Hadoop文件系统的一种，其对应的实现类
		//org.apache.hadoop.hdfs.DistributedFileSystem
		//
		FileSystem fs=FileSystem.get(new URI("hdfs://192.168.31.242:9000"),conf);
		InputStream in=fs.open(new Path("/input/result/tom-r-00000"));
		OutputStream out=new FileOutputStream(new File("tom-r-00000.txt"));
		IOUtils.copyBytes(in, out, conf);
		fs.close();

	}
	/*
	 * 测试从本地上传文件到HDFS
	 *
	 */
	@Test
	public void testPut() throws Exception{
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(new URI("hdfs://192.168.31.242:9000"),conf);
		InputStream in=new FileInputStream(new File("input.txt"));
		//路径要写到文件级别，
		OutputStream out=fs.create(new Path("/input/input.txt"));
		IOUtils.copyBytes(in, out, conf);
		fs.close();
	}
	/*
	 * 利用copyFromLocalFile/copyToLocalFile完成上传和下载
	 */
	@Test
	public void testCopy() throws Exception{
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(new URI("hdfs://192.168.31.242:9000"),conf);
		//copyFromLocalFile：本地=》HDFS 第一个path：本地文件路径
		fs.copyFromLocalFile(new Path("2.txt"),new Path("/park/2.txt"));
		//copyToLocalFile:HDFS=》本地  第一path：HDFS的文件路径
		fs.copyToLocalFile(new Path("/park/2.txt"),new Path("2.txt"));
		fs.close();
	}
	/*
	 * 创建HDFS上的文件目录
	 */
	@Test
	public void testMkdir()throws Exception{
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(new URI("hdfs://192.168.31.242:9000"),conf);
		fs.mkdirs(new Path("/park04"));
		fs.close();
	}
	/*
	 * 测试删除
	 */
	@Test
	public void testDelete()throws Exception{
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(new URI("hdfs://192.168.31.242:9000"),conf);
		//这是一个过时方法，也可以完成删除操作
		//fs.delete(new Path("/park/1.txt"));
		//如果是true，表示，如果目录是非空，也可以删除
		//如果是false，只能删除为空的目录。
		//此外，如果是删除文件的话，true和false都可以
		fs.delete(new Path("/park04"),false);
		fs.close();
	}
	/*
	 * 查看指定目录下的目录信息，以及文件信息
	 */
	@Test
	public void testListStatus()throws Exception{
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(new URI("hdfs://192.168.31.242:9000"),conf);
		FileStatus[] status=fs.listStatus(new Path("/park01"));
		for(FileStatus f:status){
			System.out.println(f);
		}
	}
	/*
	 * 递归查看文件
	 */
	@Test
	public void testListFiles()throws Exception{
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(new URI("hdfs://192.168.31.242:9000"),conf);
		//参数如果是true,会递归打印指定目录的所有目录及文件，此外注意，如果某一目录下没有文件，是不会打印此目录信息的
		//参数如果是false，不会递归打印。此外，这个方法只会在目录有文件的情况下打印
		RemoteIterator<LocatedFileStatus> it=fs.listFiles(new Path("/park01"), true);
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	/*
	 * 目录重命名
	 */
	@Test
	public void testRename()throws Exception{
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(new URI("hdfs://192.168.31.242:9000"),conf);
		fs.rename(new Path("/park01"), new Path("/park02"));
		fs.close();
	}
	/*
	 * 打印文件块信息，注意(new Path("/park02/hadoop.gz"),0,Integer.MAX_VALUE);参数的含义
	 * 此外，注意打印的信息，第一个参数表示块的起始位置，第二个参数，表示块的实际大小，第三个参数，块的存放节点位置
	 */
	@Test
	public void testGetBlockLocation()throws Exception{
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(new URI("hdfs://192.168.31.242:9000"),conf);
		BlockLocation[] bl=fs.getFileBlockLocations(new Path("/park02/hadoop.gz"),0,Integer.MAX_VALUE);
		for(BlockLocation b:bl){
			System.out.println(b);
		}
	}
}
