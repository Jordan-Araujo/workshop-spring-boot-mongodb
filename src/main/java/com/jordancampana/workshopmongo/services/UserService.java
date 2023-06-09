package com.jordancampana.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordancampana.workshopmongo.domain.User;
import com.jordancampana.workshopmongo.dto.UserDTO;
import com.jordancampana.workshopmongo.repository.UserRepository;
import com.jordancampana.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Id não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}	
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User newObj) {
		User obj = findById(newObj.getId());
		updateData(obj, newObj);
		return repo.save(obj);
	}
	
	public void updateData (User old, User update) {
		old.setName(update.getName());
		old.setEmail(update.getEmail());
	}
	
	public User fromDTO(UserDTO obj) {
		return new User(obj.getId(), obj.getName(), obj.getEmail());
	}
}