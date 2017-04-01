package com.github.sohlich.service.impl;

import com.github.sohlich.exception.CannotProcessDocumentException;
import com.github.sohlich.io.LimitedURLReader;
import com.github.sohlich.model.PresentationDocument;
import com.github.sohlich.repository.PresentationRepository;
import com.github.sohlich.service.MarkdownProcessingService;
import com.github.sohlich.service.URLProcessingService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;

/**
 * Created by Radomir Sohlich on 27/03/2017.
 */
@Service
public class URLProcessingServiceImpl implements URLProcessingService {


    @Value("${upload.size.max}")
    private Integer maxUploadInKb;

    private Logger log = LoggerFactory.getLogger(URLProcessingService.class);

    @Autowired
    private PresentationRepository presentationRepository;


    @Autowired
    private MarkdownProcessingService markdownProcessingService;

    @Override
    public PresentationDocument process(String url) {
        try {
            try(LimitedURLReader reader = new LimitedURLReader(url, maxUploadInKb)) {
                String content = reader.read();
                String hex = DigestUtils.md5DigestAsHex(url.getBytes());
                String html = markdownProcessingService.render(content);
                PresentationDocument doc = new PresentationDocument(hex, html);
                presentationRepository.save(doc);
                return doc;
            }
        } catch (Exception e) {
            throw new CannotProcessDocumentException(e);
        }
    }
}
