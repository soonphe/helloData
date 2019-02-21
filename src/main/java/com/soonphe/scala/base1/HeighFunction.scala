package com.connxun

import com.connxun.HeighFunction.sumCurrying

;

/**
  * 高阶函数——方法接收参数是个函数，或者方法返回是另一个函数
  * 函数可以作为另外一个函数的参数
  * 函数的返回值也是个函数
  */
class HeighFunction {

}

/**
  * 函数不依赖于类，不依赖于接口，不依赖于方法而独立存在，所以才能把它赋值给一个变量。
  * 这样可以构造匿名函数，再此基础上可以构建高阶函数。
  * 传函数给函数，也就是函数作为参数，还有就是函数的返回值也是函数
  */
object HeighFunction {
  def say(fn: String => Unit, name: String) {
    fn(name) //这里返回的是函数
  }


  def main(args: Array[String]): Unit = {
    //    say((name: String) => println(name), "Spark")
    //    say(name => println(name), "Spark") //类型推导
    //    say(println(_), "Spark") //只有一个参数
    //    say(println, "Spark") //一个参数时可以直接省略

  }

  /**
    * 闭包——超出了函数的作用域，但函数中的变量依然能被访问，这就叫闭包。
    *
    * @param content
    * @return
    */
  def say(content: String) = (msg: String) => println(content + ":" + msg)

  // 两次调用say，传入不同的content，创建不同的函数返回
  // 然而content只是一个局部变量，
  val r = say("Spark") //函数调用结束，内部的局部变量值就释放
  //传入content的值 ，后面r方法依然可以访问到
  r("Good")

  /**
    * Currying柯里化——原来一个大函数的调用，变成两个连续的函数调用。
    *
    * @param x
    * @param y
    * @return
    */
  def sum(x: Int, y: Int) = x + y

  println(sum(1, 2))

  def sumCurrying(x: Int) = (y: Int) => x + y

  println(sumCurrying(1)(2))

  //这里就应用了闭包，x在第一次计算使用完毕，但在第二个函数内还使用了它
  //终极写法
  def sumCurryingFinal(x: Int)(y: Int) = x + y //终极写法
  println(sumCurryingFinal(1)(2))

  /**
    * 模式匹配
    *
    * @param data
    */
  def test(data: String) {
    data match {
      //data赋值给了x，match不仅可以匹配值，还可以匹配变量的类型
      case x if x == "big" => println("big data.")
    }
  }

  /**
    * 集合匹配 ——可以按元素，元素类型
    * @param array
    */
  def test(array: Array[String]) {
    array match {
      case Array("scala") => println("Scala") //匹配集合元素
      //对象匹配，第一个参数赋值x，第二个变量赋值y
      case Array(x, y) => println(x + "," + y)
      //spark开头，_* 代表其它元素
      case Array("spark", _*) => println("spark start...")
      //漏网之鱼
      case _ => println("unknow")
    }
  }

  test(Array("scala"))
  test(Array("spark","hadoop"))
  test(Array("hadoop","storm"))
  test(Array("spark","hadoop","storm"))
  test(Array("storm"))

  /**
    * 隐形参数
    * @param a
    * @param b
    * @return
    */
  def fn(a:Int)(implicit b:Int) = {
    a+b
  }
  println(fn(3)(4))
  implicit val b = 10	//类型必须和定义相同
  println(fn(3))			//不传值则去找implicit的定义，找到后作为参数值

  /**
    * 高阶函数
    */
  val list = Array(1,2,3,4)
  //reduce化简，大数据中的reduce的参数是键值对，而这里可以是任意对象
  list.reduce{ (x,y) => x+y }
  list.reduce{ (x,y) => println(s"x:$x y:$y");x+y } //打印x和y的值


}