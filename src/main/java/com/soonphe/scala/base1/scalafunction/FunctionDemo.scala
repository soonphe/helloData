
package com.soonphe.scala.base1.scalafunction

/**
 * @Author：soonphe
 * @Date：2019-02-22 12:55
 * @Description：  * 方法定义 | 循环
 */
class FunctionDemo {

  /**
    * 基础方法定义
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

}


/**
  * 伴生对象
  */
object FuntionDemo {
  def main(args: Array[String]): Unit = {

    var funtion = new FunctionDemo()
    funtion.sayHello("Scala")
    funtion.funntion1
    System.out.println("匿名函数")
    funtion.funntion2
    System.out.println("Option选项")
    funtion.function3
  }
}



