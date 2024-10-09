package com.okta.springbootmongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Controller
public class RootController {
  
  @GetMapping("/")
  @ResponseBody
  public
  Flux<String> getRoot() {
    return Flux.just("Alive");
  }

}