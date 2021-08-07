package com.example.jokesdatabank.Repository;

import com.example.jokesdatabank.Model.Jokes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokesRepository extends JpaRepository<Jokes, Long> {

//    public List<Jokes> findAllByName(String name);

    @Query("Select j from Jokes j where j.type = ?1")
    List<Jokes> findAllByType (String name);

    @Query("select j from Jokes j where j.punchline = ?1")
    List<Jokes> findAllByPunchline (String name);
}
