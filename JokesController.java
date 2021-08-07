package com.example.jokesdatabank.Controller;

import com.example.jokesdatabank.Model.Jokes;
import com.example.jokesdatabank.Repository.JokesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jokes")
public class JokesController {

    public static String BASE_URL = "https://official-joke-api.appspot.com/random_joke";
    public static String BASE_URL_lIST = "https://official-joke-api.appspot.com/random_ten";

    @Autowired
    public JokesRepository jokesRepository;

//    @GetMapping("/")
//    public List<Jokes> findAll(){
//        return jokesRepository.findAll();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Jokes> jokes = jokesRepository.findById(id);
        if (jokes.isPresent()){
            return new ResponseEntity<>(jokes.get(), HttpStatus.OK);
        } return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllAndById(@RequestParam (required = false) Long id){
        if (id != 0){
            Optional<Jokes> jokes = jokesRepository.findById(id);
            if(jokes.isPresent()){
                return ResponseEntity.ok(jokes.get());
        }
        } return new ResponseEntity<>(jokesRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/sql/type")
    public ResponseEntity<?> findByJokesType(@RequestParam String type){
        return ResponseEntity.ok(jokesRepository.findAllByType(type));
    }

    @GetMapping("/sql/punchline")
    public ResponseEntity<?> findByJokesPunchline(@RequestParam String punchline){
        return ResponseEntity.ok(jokesRepository.findAllByPunchline(punchline));
    }

//    @GetMapping("/api/jokes/")
//    public ResponseEntity<?> findIdByQuery (@RequestParam Long id){
//        Optional<Jokes> jokes = jokesRepository.findById(id);
//        if (jokes.isPresent()){
//            return new ResponseEntity<>(jokes.get(), HttpStatus.OK);
//        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PostMapping("/myjoke")
    public ResponseEntity<?> saveJokes(@RequestBody @Valid Jokes jokes){
       return new ResponseEntity<>(jokesRepository.save(jokes), HttpStatus.CREATED) ;
    }

    @PostMapping("/newjoke")
    public ResponseEntity<?> insertNewJokes(){
        RestTemplate restTemplate = new RestTemplate();
        @Valid ResponseEntity<Jokes> responseEntity = restTemplate.getForEntity(BASE_URL, Jokes.class);
        Jokes jokes = jokesRepository.save(responseEntity.getBody());
        return new ResponseEntity<>(jokes, HttpStatus.CREATED);
    }

    @PostMapping("/new10jokes")
    public ResponseEntity<?> insertSomeNewJokes(){
        RestTemplate restTemplate = new RestTemplate();
        @Valid ResponseEntity<Jokes[]> responseEntity = restTemplate.getForEntity(BASE_URL_lIST, Jokes[].class);
        List<Jokes> jokes = jokesRepository.saveAll(Arrays.asList(responseEntity.getBody()));
        return new ResponseEntity<>(jokes, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJokes(@PathVariable Long id){
        jokesRepository.findById(id);
        return new ResponseEntity<>("Das Jokes mit Id " + id + " wurde gelöscht", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteJokesByQuery(@RequestParam Long id){
        jokesRepository.findById(id);
        return new ResponseEntity<>("Das Jokes mit Id " + id + " wurde gelöscht", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateJokes(@RequestParam Long id, @RequestBody Jokes jokesBody){
        Optional<Jokes> jokes = jokesRepository.findById(id);
        if (jokes.isPresent()){
            Jokes updateJokes = jokes.get();
            updateJokes.setType(jokesBody.getType());
            updateJokes.setPunchline(jokesBody.getPunchline());
            updateJokes.setSetup(jokesBody.getSetup());
            return new ResponseEntity<>(jokesRepository.save(updateJokes), HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
