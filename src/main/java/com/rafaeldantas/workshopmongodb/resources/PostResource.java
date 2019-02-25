package com.rafaeldantas.workshopmongodb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafaeldantas.workshopmongodb.domain.Post;
import com.rafaeldantas.workshopmongodb.resources.util.URL;
import com.rafaeldantas.workshopmongodb.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@GetMapping(value = "/titleSearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		return ResponseEntity.ok().body(service.findByTitle(text));
	}
	
}
