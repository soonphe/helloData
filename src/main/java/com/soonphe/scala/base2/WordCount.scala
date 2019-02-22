package com.soonphe.scala.base2

import scala.io.Source

/**
 * @Author：soonphe
 * @Date：2019-02-22 13:12
 * @Description：单词统计
 */
object WordCount {
  def main(args: Array[String]): Unit = {

    //统计wordcount
    val list = Source.fromFile("d:/words.txt").getLines().toList //一次读一行
      .map { x => (x, 1) } //键每一个单词映射为一个元祖（key，1）
      .groupBy { x => x._1 } //分组，同一个key分在同一个list中
      .mapValues { list => list.map { t => t._2 }.reduce { (x, y) => x + y } } //组内求和
      .foreach { x => println(x) }

    //下划线方式改造wordcount
    //    val list2= scala.io.Source.fromFile("d:/words.txt").getLines().toList
    //      .map { (_,1) }
    //      .groupBy { _._1 }
    //      .mapValues{ _.map{ _._2 }.reduce{ _+_ } }
    //
    //结果保存到文件中
    //    val out = new java.io.PrintWriter("c:/result.txt")
    //    lines.foreach {
    //      out.println(_)
    //    }
    //    out.close

  }

  /**
    * 单词统计步骤解析
    */
  def wordCount(): Unit = {
    val lines = List("hello tom hello jerry", "hello jerry", "hello kitty")
    //实现1：
    //1.lines.flatMap(_.split(" ")) 将集合中的单词进行压平，其中每个元素通过" "来切分
    //执行结果为：List[String] = List(hello, tom, hello, jerry, hello, jerry, hello, kitty)
    //2.lines.flatMap(_.split(" ")).map((_, 1))统计每一个单词出现的次数，出现一次计数1
    //执行结果为：List[(String, Int)] = List((hello,1), (tom,1), (hello,1), (jerry,1), (hello,1), (jerry,1), (hello,1), (kitty,1))
    //3.groupBy(_._1)将集合进行分组，分组是按照集合中每一个元组如（hello，1）的第一个单词（_._1）进行分组
    //执行结果为：scala.collection.immutable.Map[String,List[(String, Int)]] = Map(tom -> L
    //ist((tom,1)), kitty -> List((kitty,1)), jerry -> List((jerry,1), (jerry,1)), hel
    //lo -> List((hello,1), (hello,1), (hello,1), (hello,1)))
    //4.mapValues(_.foldLeft(0)(_+_._2)) 获取map中的所有values如 List((tom,1))，
    // foldLeft进行统计，(0)初始化参数为0，_+_._2进行求和，第一个_在循环时可能为初始化参数和进行求和后的值
    //执行结果为：scala.collection.immutable.Map[String,Int] = Map(tom -> 1, kitty -> 1, jerry -> 2, hello -> 4)
    lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.foldLeft(0)(_ + _._2))

    //实现2：
    lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).map(t => (t._1, t._2.size)).toList.sortBy(_._2).reverse
  }
}

