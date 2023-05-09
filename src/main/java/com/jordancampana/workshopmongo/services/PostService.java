package com.jordancampana.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordancampana.workshopmongo.domain.Post;
import com.jordancampana.workshopmongo.repository.PostRepository;
import com.jordancampana.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Id não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
}