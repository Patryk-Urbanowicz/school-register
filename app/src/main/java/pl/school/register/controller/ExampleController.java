package pl.school.register.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/api")
public class ExampleController {

    @GetMapping(value = "/example")
    public String helloWorld(){
        return "Hello World!";
    }
}
