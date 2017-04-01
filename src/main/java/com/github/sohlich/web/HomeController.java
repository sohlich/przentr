package com.github.sohlich.web;

import com.github.sohlich.model.PresentationDocument;
import com.github.sohlich.service.URLProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by Radomir Sohlich on 26/03/2017.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private URLProcessingService processingService;

    @GetMapping
    public String get() {
        return "index";
    }

    @PostMapping
    public RedirectView post(@RequestParam("presentation") String presentationUrl) {
        PresentationDocument document = processingService.process(presentationUrl);
        RedirectView mv = new RedirectView("slides/"+document.getHex());
        return mv;
    }

}
