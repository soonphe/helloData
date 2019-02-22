package com.soonphe.scala.base1.scalaclass

/**
 * @Author：soonphe
 * @Date：2019-02-22 11:36
 * @Description：Class类，这里的val相当于声明的成员变量
 */
class Person(val name: String){

  def getName(): Unit={
    println(this.name)
  }
}

/**
  * 伴生对象
  */
object Person {
  def main(args: Array[String]): Unit = {
    println((new Person("tony")).name)
  }
}

