package io.anush.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

	@RequestMapping("/deptFallBack")
	public Mono<String> getdeptFallback() {
		return Mono.just("Service goes down or its getting slow");

	}

}
