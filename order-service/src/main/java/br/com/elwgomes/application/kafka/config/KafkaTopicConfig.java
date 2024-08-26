package br.com.elwgomes.application.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

  @Value("${topics.order.request.topic}")
  private String orderTopic;

  @Bean
  NewTopic orderTopicBuilder() {
    return TopicBuilder.name(orderTopic).partitions(1).replicas(1).build();
  }

}
