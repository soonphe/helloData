//package com.connxun.akka
//
//import scala.actors._, akka.actor.Actor._
/**
  * 第一个actor示例
  */
//object TestActor{
//  def main(args: Array[String]): Unit = {
//    val badActor = actor{
//      receive{    //接收消息
//        //注意：如果消息类型不一致，消息会被忽略掉
//        case msg:Int  => println(msg)
//      }
//    }
//
//    badActor ! "今天感觉如何，娜娜?"  //发送消息
//  }
//}