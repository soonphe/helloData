package com.connxun

/**
  * 继承类
  * 如果参数是var类型，就不能进行override
  * case类型默认两个参数的类型就是val所以可以省略不写。
  * @param name
  * @param age
  */
class Student1(  name:String,  age:Int) extends Person(name){

}