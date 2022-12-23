package com.project.shoppingAndNoticeBoard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping(value = "/")
    public String test() {
        return "test";
    }
}
