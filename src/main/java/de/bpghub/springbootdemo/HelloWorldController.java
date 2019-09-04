package de.bpghub.springbootdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/")
    public String greeting() {
        return "Hello, world. I've been build using a Jenkinsfile.";
    }

}
