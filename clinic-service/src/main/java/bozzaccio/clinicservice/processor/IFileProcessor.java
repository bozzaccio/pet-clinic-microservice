package bozzaccio.clinicservice.processor;

import org.apache.camel.Processor;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.scheduler.Schedulers;

import java.io.*;
import java.util.Objects;

public interface IFileProcessor extends Processor {

    String IN_FILE_HEADER = "inputFile";
    String OUT_FILE_HEADER = "outputFile";

    default InputStream convertFile(FilePart file) {
        Objects.requireNonNull(file, "File cannot be null!");

        PipedOutputStream outputStream = new PipedOutputStream();

        try (PipedInputStream inputStream = new PipedInputStream(outputStream)) {
            DataBufferUtils.write(file.content(), outputStream)
                    .subscribeOn(Schedulers.elastic())
                    .doOnComplete(() -> {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .subscribe(DataBufferUtils.releaseConsumer());
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    default byte[] convertStreamToByteArray(InputStream stream) throws IOException {
        Objects.requireNonNull(stream, "Input Stream cannot be null!");

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        return buffer.toByteArray();
    }
}
