package com.github.sohlich.service.impl;

import com.github.sohlich.service.MarkdownProcessingService;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.springframework.stereotype.Service;

/**
 * Created by Radomir Sohlich on 29/03/2017.
 */
@Service
public class MarkdownProcessingServiceImpl implements MarkdownProcessingService {

    @Override
    public String render(String content){
        MutableDataSet options = new MutableDataSet();

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(content);
        String html = renderer.render(document);
        return html;
    }
}
