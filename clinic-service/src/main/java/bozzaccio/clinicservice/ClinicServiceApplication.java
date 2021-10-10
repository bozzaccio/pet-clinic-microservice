package bozzaccio.clinicservice;

import bozzaccio.clinicservice.route.CreatePetVisitRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ClinicServiceApplication {

    @EndpointInject(CreatePetVisitRoute.ROUTE_URI)
    ProducerTemplate producer;

    public static void main(String[] args) {
        SpringApplication.run(ClinicServiceApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws Exception {

        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new CreatePetVisitRoute());
        camelContext.start();

        producer.sendBody("My Body");
    }

}
