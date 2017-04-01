package com.github.sohlich.web;

import com.github.sohlich.model.PresentationDocument;
import com.github.sohlich.repository.PresentationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Radomir Sohlich on 30/03/2017.
 */
@Controller
@RequestMapping("/slides")
public class SlidesController {

    private Logger log = LoggerFactory.getLogger(SlidesController.class);

    @Autowired
    private PresentationRepository repository;

    @GetMapping("/{document}")
    public ModelAndView get(@PathVariable("document") String document) {
        PresentationDocument doc = repository.findById(document);
        String[] slides = new String(doc.getContent()).split("<hr />");
        ModelAndView mw = new ModelAndView();
        mw.setViewName("slides");
        mw.addObject("slides",slides);
        return mw;
    }

}
