
package com.connxun

import scala.collection.mutable.ArrayBuffer

class HelloScala {
  /**
    * 参数打印
    * scala:
    * def 方法名（参数：类型[泛型]）：返回值={
    * 表达式1；表达式2
    * 表达式3
    * 表达式4
    * }
    *
    * @param x
    */
  def sayHello(x: String): Unit = {
    println("hello," + x);
  }

  /**
    * 循环判断
    */
  def funntion1(): Unit = {
    val array = Array(1, 2, 3, 4, 5)

    val array3 = for (x <- array if x > 2) yield x
    for (y <- array3) {
      println(y)
    }

  }

  /**
    * 匿名函数
    */
  def funntion2(): Unit = {
    val a = Array(1, 2, 3, 4, 5)
    a.partition((n: Int) => {
      n % 2 != 0
    })
  }

  /**
    * Option选项类型
    *
    * @param a
    * @param b
    * @return
    */
  def div(a: Int, b: Int): Option[Int] = {
    //Option类型在Scala中用来表示一个结果，它可能有值，也可能没有值，
    // 它有两个子类Some表示有值，None表示没有值
    if (b != 0) Some(a / b) else None

  }

  def function3(): Unit = {
    println(div(10, 5)) // 返回 Some(2)
    println(div(10, 0)) // 返回 None

    println("getOrElse会在Option为None时采用默认值")
    val x1 = div(10, 5).getOrElse(0)
    val x2 = div(10, 0).getOrElse(0)
    println(x1)
    println(x2)

    println("测试foreach，返回结果为空时，不会进入其后的循环")
    val x11 = div(10, 5)
    val x22 = div(10, 0)
    x11.foreach { x => println(x) }
    x22.foreach { x => println(x) }
  }

  /**
    * 集合
    *
    * @return
    */
  def function4(): Unit = {
    println("Array数组")
    //取1到11之间的数值，步长为2，_*代表把每个元素提出来
    val list = Array[Int](1 to 11 by 2: _*)
    //集合新增——它会创建一个新的list，最后一个元素是6
    list.:+(6)  //后加
    6 +: list  //前加
    for (y <- list) {
      println(y)
    }

    //集合合并
    val lista = List(1,2)
    val listb = List(5,6)
    println(lista ::: listb)	//使用三个冒号连接

    //中间插入元素
//    println(list.take(2) ::: List(0) List::: list.takeRight(2))
//    删除左侧元素
//    println(list.drop(2))		//删除左边的2个元素
//    println(list.dropRight(2))		//删除右边的2个元素
    //更新元素——原则是重新创建一个新的数组，而不是真正更新
    println(list.updated(2, 30))

    println("list数组——相当于java中LinkedList")
    //定于固定长度10个2
    val list2 = List.fill(10)(2)
    //定义10个随机字母
    val list3 = List.fill(10)(scala.util.Random.nextPrintableChar)
    //定义10个随机数，随机数在0~100之间
    val list4 = List.fill(10)(scala.util.Random.nextInt(100))

  }

  /**
    * 可变数组
    */
  def function5():Unit ={

    val buffer = ArrayBuffer(1,2,3,4,5)
    buffer += 6
    println(buffer)

    //Seq类似数组，sum将其所有值进行累加。
    val seq = Seq(1,2,3,4)
    seq.sum

    //反转
    1 to 5 reverse;
    //判断包含
    1 to 5 contains 3
    //判断包含一段
    1 to 5 containsSlice (2 to 4)
    //判断开头
    (1 to 5) startsWith (1 to 3)

    //map对象
    val map = Map(1 -> "北京", 2 -> "上海")	//->前面是key，后面是值
    val map2 = map + (3 -> "广州")	//产生新的map
    val map3 = map -2					//删除key=2的元素
    println(map)
    println(map2)

    //Tuple元组——键值对，且一个键可以对应多个值，但不是无限的，最多22个
    val t1 = (1,"北京","100086",108.08,new java.util.Date())
    println(t1._1)		//访问key，注意tuple下标是从1开始
    println(t1._2)		//获取北京
    println(t1._3)		//获取邮编
  }



}


