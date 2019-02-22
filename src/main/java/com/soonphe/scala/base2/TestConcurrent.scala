package com.soonphe.scala.base2

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
//不导入此包下面会有提示错误
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * 并发（异步）
  */
object TestConcurrent {
  def main2(args: Array[String]): Unit = {
    //创建了Future对象，scala就把它关联到线程池，然后执行future中的代码，
    // 返回结果就自动触发了onSuccess事件通过case语句就可以得到返回结果。
    var fu = Future{
      println("开始运行计算")
      Thread.sleep(200)
      100 //返回值
    }
    
    fu.onSuccess{			//成功后通过此事件触发
        case x => println(x)
    }
    
    Thread.sleep(1000)    //主线程等1秒，要大于fu的延迟时间，必须写，否则无法执行！
    //解析：程序再很短时间内跑完，多线程任务还未返回就被杀死，所有需要主线程等待
  }

  /**
    * 同步方式
    * @param args
    */
  def main(args: Array[String]): Unit = {
    /**
      * JAVA中难点在于线程之间如何通信
      * Scala借助Future底层已经实现互相通信
      */
    var fu1 = Future{
      println("f1开始运行计算")
      Thread.sleep(200)
      100 //返回值
    }
    val fu2 = Future{
      println("f2开始运行计算")
      Thread.sleep(300)
      200
    }

    //yield把结果放到一个新的数组中
    val c = for(a <- fu1; b <- fu2) yield (a+b)   //并发的 ——fu1和fu2执行不分先后
    println(Await.result(c, Duration.Inf))        //阻塞的，

    //也可以主线程得到返回值，主线程是阻塞的
//    val r = Await.result(fu, Duration.Inf)  //持久化：永久
//    println(r)
  }

}