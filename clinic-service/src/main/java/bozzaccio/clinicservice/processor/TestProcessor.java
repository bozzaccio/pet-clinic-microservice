package bozzaccio.clinicservice.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TestProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("Camel is working!");
        System.out.println(exchange.getIn().getBody());
    }
}
