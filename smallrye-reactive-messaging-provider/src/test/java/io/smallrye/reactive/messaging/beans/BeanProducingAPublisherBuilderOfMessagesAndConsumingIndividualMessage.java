package io.smallrye.reactive.messaging.beans;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BeanProducingAPublisherBuilderOfMessagesAndConsumingIndividualMessage {

  @Incoming("count")
  @Outgoing("sink")
  public PublisherBuilder<Message<String>> process(Message<Integer> message) {
    return ReactiveStreams.of(message)
      .map(Message::getPayload)
      .map(i -> i + 1)
      .flatMapRsPublisher(i -> Flowable.just(i, i))
      .map(i -> Integer.toString(i))
      .map(Message::of);
  }

}
