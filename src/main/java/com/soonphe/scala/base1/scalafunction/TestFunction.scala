package com.soonphe.scala.base1.scalafunction

/**
  * 函数式编程
  * 函数可以不依赖于类，不依赖于接口，不依赖于方法而独立存在，所以才能把它赋值给一个变量
  * 函数式编程核心价值在于计算是不仅传递数据还传递算法
  */
class TestFunction {

  def fn(name: String) {
    println(name)
  }

  val f = fn _ //fn _就代表fn函数的原型

  f("Spark")
  fn("Spark")
  fn("Scala")

  // 返回值是函数——定义方法，方法体是一个函数
  def fn2(msg1: String) = (msg2: String) => println(msg1 + " " + msg2)

  val f2 = fn2("you're")
  f2("good")

}

object TestFunction {
  def main(args: Array[String]): Unit = {
    //按奇偶数将集合划分为两个
    val f2 = (n: Int) => {
      n % 2 != 0
    } //函数遇到奇数返回true，偶数返回false
    val array = Array(1, 2, 3, 4, 5) //初始化数组
    println(array.partition(f2)) // partition分区的意思，函数做参数

    //还可以简写为匿名函数
    println(array.partition((n: Int) => {
      n % 2 != 0
    }))
  }
}
