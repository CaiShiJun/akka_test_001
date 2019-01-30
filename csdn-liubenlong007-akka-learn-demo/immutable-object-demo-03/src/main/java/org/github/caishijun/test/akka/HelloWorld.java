package org.github.caishijun.test.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

public class HelloWorld extends UntypedActor {

    ActorRef greeter;

    @Override
    public void preStart() {
        // 创建一个接待员行为角色
        greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter actor path：" + greeter.path());
        // 告诉它执行问候语
        greeter.tell(new Message(2, Arrays.asList("2", "dsf")), getSelf());
    }

    @Override
    public void onReceive(Object msg) {
        try {
            System.out.println("HelloWorld收到的数据为：" + JSONObject.toJSONString(msg));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
