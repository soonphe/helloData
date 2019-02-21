package com.connxun

/**
  * 单例类Object
  * 与类名一致的object 称为该类的伴生对象，
  * object类可以用来构建工具类和静态全局的常量，因为它只实例化一次
  */
object SayHello {
  println("init once") //只第一次访问被执行
  val say = "hello"

  def getSay = say
}

