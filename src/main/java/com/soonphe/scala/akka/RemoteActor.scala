//package com.connxun.akka
//
//import akka.actor.{Actor, ActorSystem, Props}
//
///**
//  * 远程互相发消息
//  */
////测试远程Actor
//object RemoteActor {
//  class Actor4 extends Actor{
//    override def receive = {
//      case msg:String => println(msg)
//    }
//  }
//
//  def main(args: Array[String]): Unit = {
//    //参数配置
//    val conf = new java.util.HashMap[String,Object]()
//    val IP = "127.0.0.1"
//    val PORT = "2550"
//
//    val list = new java.util.ArrayList[String]()
//    list.add("akka.remote.netty.tcp")
//
//    conf.put("akka.remote.enabled-transports", list)  //参数是个集合
//    conf.put("akka.actor.provider", "akka.remote.RemoteActorRefProvider")
//    conf.put("akka.remote.netty.tcp.hostname", IP)
//    conf.put("akka.remote.netty.tcp.port", PORT)
//
//    val sys = ActorSystem("master", ConfigFactory.parseMap(conf))
//    sys.actorOf(Props[Actor4], "jt")  //设定Actor的名字
//    //访问地址akka.tcp://ActorSystem名称@IP:端口/user/jt      [akka.tcp://master@127.0.0.1:2550]
//  }
//}