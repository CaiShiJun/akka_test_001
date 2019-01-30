package org.github.caishijun.test.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class FirstWayStart {

    public static void main(String[] args) {
        //创建ActorSystem。一般来说，一个系统只需要一个ActorSystem。
        //参数1：系统名称。参数2：配置文件
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("akka.config"));
        ActorRef myWork = system.actorOf(Props.create(MyWork.class), "MyWork");
        ActorRef watchActor = system.actorOf(Props.create(WatchActor.class, myWork), "WatchActor");

        myWork.tell(MyWork.Msg.WORKING, ActorRef.noSender());
        myWork.tell(MyWork.Msg.DONE, ActorRef.noSender());

        //中断myWork
        myWork.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
    /**
     * [INFO] [01/29/2019 17:46:48.230] [Hello-akka.actor.default-dispatcher-5] [akka://Hello/user/MyWork] myWork starting.
     * [INFO] [01/29/2019 17:46:48.234] [Hello-akka.actor.default-dispatcher-5] [akka://Hello/user/MyWork] i am  working
     * [INFO] [01/29/2019 17:46:48.234] [Hello-akka.actor.default-dispatcher-5] [akka://Hello/user/MyWork] stop  working
     * [INFO] [01/29/2019 17:46:48.239] [Hello-akka.actor.default-dispatcher-5] [akka://Hello/user/MyWork] myWork stoping..
     * [ERROR] [01/29/2019 17:46:48.273] [Hello-akka.actor.default-dispatcher-3] [akka://Hello/user/WatchActor] akka://Hello/user/MyWork has Terminated. now shutdown the system
     */

}
