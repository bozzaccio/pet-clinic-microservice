package bozzaccio.clinicservice.route;

import bozzaccio.clinicservice.processor.TestProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CreatePetVisitRoute extends RouteBuilder {

    public static final String ROUTE_URI = "direct:createPetVisitRoute";


    @Override
    public void configure() throws Exception {

        from(ROUTE_URI)
                .process(new TestProcessor())
                .end();
    }
}
