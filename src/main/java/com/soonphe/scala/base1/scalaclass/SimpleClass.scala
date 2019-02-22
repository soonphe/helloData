package com.soonphe.scala.base1.scalaclass

/**
  * @Author soonphe
  * @Date 2019-02-22 11:34
  * @Description 单例类
  */
/**
  * 单例类Object
  * 与类名一致的object 称为该类的伴生对象，
  * object类可以用来构建工具类和静态全局的常量，因为它只实例化一次
  */
object SimpleClass {
  println("init once") //只第一次访问被执行
  val say = "hello"

  def getSay = say

  def main(args: Array[String]): Unit = {
    //Object单例类
    SimpleClass.getSay
    SimpleClass.getSay
  }
}