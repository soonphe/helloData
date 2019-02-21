package com.connxun;


/**
 * @Author anna
 * @Date 2017-11-28 16:25
 * @Description
 */
public class HelloTest {

    public static void main(String[] args) {
        HelloScala h = new HelloScala();
        h.sayHello("scala");
        System.out.println("循环判断");
        h.funntion1();
        System.out.println("匿名函数");
        h.funntion2();
        System.out.println("Option选项");
        h.function3();
        System.out.println("集合");
        h.function4();

        //Object单例类
        SayHello.getSay();
        SayHello.getSay();
        //Class类
        new Person("tony").getName();
    }


}