package de.bpghub.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private CounterRepository counterRepository;

    @RequestMapping("/")
    public String greeting() {

        Counter berlin = counterRepository.findByName("berlin");
        if (berlin == null) {
            berlin = new Counter();
            berlin.setName("berlin");
        }
        berlin.setCount(berlin.getCount() + 1);
        counterRepository.save(berlin);

        return "Hello, world. I've been build using a Jenkinsfile. Counter: " + berlin.getCount();
    }

}
