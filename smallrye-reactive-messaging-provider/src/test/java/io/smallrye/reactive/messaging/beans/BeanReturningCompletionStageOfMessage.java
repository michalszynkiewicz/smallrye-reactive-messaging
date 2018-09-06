package io.smallrye.reactive.messaging.beans;

import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class BeanReturningCompletionStageOfMessage {

  private AtomicInteger count = new AtomicInteger();

  @Outgoing("infinite-producer")
  public CompletionStage<Message<Integer>> create() {
    return CompletableFuture.supplyAsync(() -> Message.of(count.incrementAndGet()));
  }

}
