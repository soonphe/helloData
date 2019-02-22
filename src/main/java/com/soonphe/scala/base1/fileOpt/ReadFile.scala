package com.soonphe.scala.base1.fileOpt

import scala.io.Source

/**
  * 读文件
  */
object ReadFile {
  def main(args: Array[String]): Unit = {
    println("读文件")
    val lines = Source.fromFile("d:/n.txt")("UTF-8").getLines()
    lines.foreach {
      println _
    }
  }
}