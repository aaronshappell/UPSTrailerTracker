package com.ups.UPSTrailerTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    @Autowired
    private GreetingRepository greetingRepository;

    public Greeting addGreeting(Greeting greeting){
        greetingRepository.save(greeting);
        return greeting;
    }

    public Iterable<Greeting> getGreetings(){
        return greetingRepository.findAll();
    }
}
