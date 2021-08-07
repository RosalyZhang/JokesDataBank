package com.example.jokesdatabank;

import com.example.jokesdatabank.Model.Jokes;
import com.example.jokesdatabank.Repository.JokesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JokesDatabankApplication implements CommandLineRunner {

    @Autowired
    public JokesRepository jokesRepository;

    public static void main(String[] args) {
        SpringApplication.run(JokesDatabankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jokesRepository.save(new Jokes("General", "What´s a best thing about a Boolean?", "Even if your´re wrong, wou´re only off by a bit."));
        jokesRepository.save(new Jokes ("General", "How long should my essay be?", "As long as a girl's skirt: long enough to cover everything that needs to be covered, but short enough to keep me interested"));
    }
}
