package com.rafaeldantas.workshopmongodb.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaeldantas.workshopmongodb.domain.Post;
import com.rafaeldantas.workshopmongodb.repository.PostRepository;
import com.rafaeldantas.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public List<Post> findAll() {
		return repo.findAll();
	}

	public Post findById(String id) {
		Optional<Post> Post = repo.findById(id);
		return Post.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Post.class.getName()));
	}

	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}

	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		return repo.fullSearch(text, minDate, new Date(maxDate.getTime() + 24 * 60 * 60 * 1000));
	}

}
