package br.com.elwgomes.application.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class InitConfig implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    throw new UnsupportedOperationException("Unimplemented method 'run'");
  }

}
