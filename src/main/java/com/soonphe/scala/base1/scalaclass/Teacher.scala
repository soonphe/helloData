package com.soonphe.scala.base1.scalaclass

/**
  * 样例类实现了序列化，同时默认实现了很多方法，例如toString、equals、hashCode等方法
  * @param name
  * @param age
  */
case class Teacher(var name:String, age:Int){
  def teach(){
    //this和${}等价
    println(this.name)
    println(s"老师:${name}正在讲课......")
  }
  
  def add(age:Int){
    println("add "+age)
  }
}

/**
  * 伴生对象
  */
object Teacher {
  def main(args: Array[String]): Unit = {
    val t1 = Teacher("露娜",18)  //创建对象省略new关键字
    println(t1.name)
    t1.name = "李璐"
    println(t1.name)
    t1.teach()
    val t2 = Teacher("王莹",16)
    t2 add 1  //当方法是对象的属性时，括号可以去掉
  }
}