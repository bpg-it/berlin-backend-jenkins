package de.bpghub.springbootdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
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

        String greeting = "Hello, world from " + getHostname() + ". Counter: " + berlin.getCount();
        log.info("{}", greeting);
        return greeting;
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "unknown";
    }

}
