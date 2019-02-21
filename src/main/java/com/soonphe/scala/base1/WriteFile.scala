package com.connxun

import java.io.PrintWriter

/**
  * 写文件
  */
object WriteFile {
  def main(args: Array[String]): Unit = {
    println("写文件")
    val out = new PrintWriter("d:/n.txt")
    for (i <- 1 to 10) out.print(i)
    // use string format
    val num = 100
    val price = .1
    out.print("%6d %10.2f".format(num, price))
    out.close()
  }
}