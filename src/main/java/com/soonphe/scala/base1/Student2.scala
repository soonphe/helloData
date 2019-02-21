package com.connxun

/**
  * 类的继承，这里name是子类的name
  * 如果要覆盖父类属性就必须写override。
  * @param name
  * @param age
  */
class Student2(override val name:String, val age:Int)
  extends Person(name){

}