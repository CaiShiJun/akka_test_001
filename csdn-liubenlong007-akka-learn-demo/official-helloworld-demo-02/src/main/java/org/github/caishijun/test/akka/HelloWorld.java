package org.github.caishijun.test.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorld extends UntypedActor {

    @Override
    public void preStart() {
        // 创建一个接待员行为角色
        final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        // 告诉它执行问候语
        greeter.tell(Greeter.Msg.GREET, getSelf());
    }

    @Override
    public void onReceive(Object msg) {
        if (msg == Greeter.Msg.DONE) {
            // 当欢迎程序完成时，停止该角色并停止应用程序
            getContext().stop(getSelf());
        } else
            unhandled(msg);
    }
}
