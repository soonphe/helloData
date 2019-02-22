package com.soonphe.scala.base2

/**
 * @Author：soonphe
 * @Date：2019-02-22 13:15
 * @Description：par多线程并发
 */
object ParConcurrent {
  def main(args: Array[String]): Unit = {
    val startTime = System.currentTimeMillis(); //开始毫秒数
    println(startTime)
    val list = List(1, 2, 3, 4)
    list.par.reduce { (x, y) =>
      println(s"x:${x},y:${y}" +
        Thread.currentThread().getName); //当前处理线程名称
      x + y
    }

    val stopTime = System.currentTimeMillis() - startTime
    println("总共用时" + stopTime)
  }

  /**
    * map映射——将一个集合转化为另一个集合，集合中元素个数不变
    */
  val listMap = List(1,2,3,4,5,6)
  listMap.map { x => x+1 }    //每个元素+1

  /**
    * 分组groupBy
    * 运行结果：Map(
    * 上海->List((上海,3),(上海,4)),
    * 北京->List((北京,1),(北京,2) ,(北京,1) ,(北京,2),
    * 天津->List((天津,2))
    * )
    */
  val m = List(("北京", 1), ("上海", 3), ("天津", 2), ("北京", 2), ("北京", 1), ("上海", 4), ("北京", 2))
  m.groupBy { x => x._1 } //x是Tuple类型：(String,Int)  ,这里安装key进行分组

  /**
    *   * mapValues值映射——对map集合中的值做映射
    */
  val list = List(("北京",1),("上海",2),("北京",9),("广州",1))
  //这里的x为list中的每个对下的形参
  //以key分组，返回一个map对象，结果为：Map(上海 -> List((上海,2)), 北京 -> List((北京,1), (北京,9)), 广州 -> List((广州,1)))
  val map = list.groupBy( x => x._1)
  println(map)
  //只获取tuple中的值——注：list没有mapValues方法，map才有
  //mapValues：对map所有的value施加一个map函数，返回一个新的map
  //注: 这里的list只是map中每一个对象中的list形参，不是上面的list
  //只获取value对象，结果为：Map(上海 -> List(2), 北京 -> List(1, 9), 广州 -> List(1))
  map.mapValues( list => list.map(x => x._2) )
  //按各个key，将list中的值进行累加
  map.mapValues( list => list.map(x => x._2).reduce((x,y) => x+y))  //这里的reduce对集合做规约操作

  /**
    * reduce化简
    * 执行步骤：((((1+2)+3)+4)+5)
    */
  val listReduce = Array(1,2,3,4,5)
  println(listReduce.reduce((x,y) => x+y ))

}