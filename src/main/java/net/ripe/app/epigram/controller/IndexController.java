package net.ripe.app.epigram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by edwards.mukasa
 */

// tag::code[]
@Slf4j
@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String index() {
        log.info("IndexController.index()");
        return "index";
    }
}
// end::code[]