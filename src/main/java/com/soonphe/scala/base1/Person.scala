package com.connxun

/**
  * Class类，这里的val相当于声明的成员变量
  * @param name
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

