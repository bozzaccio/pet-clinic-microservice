package bozzaccio.clinicservice.processor;

import org.apache.camel.Exchange;
import org.springframework.http.codec.multipart.FilePart;

import java.io.InputStream;
import java.util.Base64;

public class FileConverterProcessor implements IFileProcessor {

    @Override
    public void process(Exchange exchange) throws Exception {

        FilePart file = exchange.getIn().getHeader(IN_FILE_HEADER, FilePart.class);


        try (InputStream stream = convertFile(file)) {
            String base64 = Base64.getEncoder().encodeToString(convertStreamToByteArray(stream));
            exchange.getIn().setHeader(OUT_FILE_HEADER, base64);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
