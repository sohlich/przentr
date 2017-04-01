package com.github.sohlich.io;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * The LimitedURLReader tries to read the content of
 * URL. But if the content exceeds the given limit,
 * it throws an exception.
 *
 * Created by Radomir Sohlich on 31/03/2017.
 */
public class LimitedURLReader implements AutoCloseable{

    private final int limit;
    private final String url;

    public LimitedURLReader(String url,int limit) {
        this.limit = limit;
        this.url = url;
    }

    public String read() throws IOException {
        try (InputStream input = new URL(url).openStream()) {
            try (BoundedInputStream bIs = new BoundedInputStream(input,
                    limit * 1024)) {
                String content = IOUtils.toString(bIs, "UTF-8");
                if (input.read() > -1) {
                    throw new MaxUploadSizeExceededException
                            (limit * 1024);
                }
                return content;
            }
        }
    }


    @Override
    public void close() throws Exception {

    }
}
