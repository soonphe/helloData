package com.soonphe.scala.base1.scalaclass

/**
 * @Author：soonphe
 * @Date：2019-02-22 11:39
 * @Description：类的继承，这里name是子类的name
  *  如果要覆盖父类属性就必须写override。
 */
class Student2(override val name:String, val age:Int)
  extends Person(name){

}