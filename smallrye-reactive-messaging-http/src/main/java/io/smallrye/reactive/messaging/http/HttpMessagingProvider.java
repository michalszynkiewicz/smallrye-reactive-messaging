package io.smallrye.reactive.messaging.http;

import io.smallrye.reactive.messaging.spi.IncomingConnectorFactory;
import io.smallrye.reactive.messaging.spi.OutgoingConnectorFactory;
import io.vertx.reactivex.core.Vertx;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.MessagingProvider;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.eclipse.microprofile.reactive.streams.operators.SubscriberBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class HttpMessagingProvider implements IncomingConnectorFactory, OutgoingConnectorFactory {

  @Inject
  private Vertx vertx;

  @Override
  public Class<? extends MessagingProvider> type() {
    return Http.class;
  }

  @Override
  public SubscriberBuilder<? extends Message, Void> getSubscriberBuilder(Config config) {
    return new HttpSink(vertx, config).sink();
  }

  @Override
  public PublisherBuilder<? extends Message> getPublisherBuilder(Config config) {
    return new HttpSource(vertx, config).source();
  }


//  @Override
//  public CompletionStage<Subscriber<? extends Message>> createSubscriber(Map<String, String> config) {
//    return new HttpSink(vertx, ConfigurationHelper.create(config)).get();
//  }


}
