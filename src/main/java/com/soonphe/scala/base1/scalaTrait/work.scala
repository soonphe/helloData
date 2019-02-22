package com.soonphe.scala.base1.scalaTrait

/**
 * @Author：soonphe
 * @Date：2019-02-22 13:25
 * @Description：Trait(特征)：相当于Java接口，使用Trait可以实现多继承
 */
trait work {
  def todo()    //抽象方法，待子类实现
  def doing(){
    println("doing....")
  }
}

case class task() extends work{
  def todo(){
    println("to do something")
  }
}

object test{
  def main(args: Array[String]): Unit = {
    //val o = work  //抽象类不能实例化
    val o = task();
    o.todo
    o.doing

    //下划线
    val list = Array(1,2,3,4,5)
    list.foreach { x => println(x) }		//正常写法
    list.foreach {println(_)}	//简写，下划线可以想象成是一个填空，把x一个一个填过来
    println(list.reduce{ (x,y) => x+y })  //等于list.sum
    println(list.reduce{ _+_ })

    println()
    //方法名 空格 _ 会把方法变成一个函数
    //把方法变成一个函数 (a,b,c) => sum(a,b,c)
    val fn = sum _          //函数即对象，方法变函数，其实内部就是把方法变成内部对象。
    println(sum(1,2,3))			//方法调用
    println(fn(1,2,3))			//函数调用

    //部分参数
    val y = sum(1,2,_:Int)	 //防止可能调用重载方法，必须指定最后的类型，下划线代表未赋值的参数
    y(5)                     // 生成的函数 (c) => sum(1,2,c)
    val z = sum(1,_:Int,_:Int)
    z(6,7)

  }

  def sum(a:Int, b:Int, c:Int) = a+b+c

}
