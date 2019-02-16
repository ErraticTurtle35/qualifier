package org.delivery.qualifier;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController{

    @Value("${nouns}")
    List<String> nouns;

    @Value("${adjectives}")
    List<String> adjectives;

    @GetMapping("/")
    public String index(){
        return "Spring boot application";
    }

    @GetMapping("/qualify/{name}")
    public String qualifyName(@PathVariable("name") String name){
        return String.format("%s is a %s %s", name, adjective(), noun());
    }

    private String adjective(){
        return randomValue(adjectives);
    }

    private String noun(){
        return randomValue(nouns);
    }

    private String randomValue(List<String> list){
        return list.get(new Random().nextInt(list.size()));
    }
}