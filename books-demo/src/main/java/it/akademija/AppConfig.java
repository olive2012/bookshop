package it.akademija;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
//importresource "prijungia" XML konfiguracija prie sitos Anotacijomis paremtos konfiguracijos klases
public class AppConfig {
    public AppConfig() {
    }
}