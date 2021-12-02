package com.apress.AWS.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apress.AWS.dto.UserDTO;
import com.apress.AWS.repository.UserJpaRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserJpaRepository useRepository;

	public List<UserDTO> listAll() {
		return useRepository.findAll();
	}

	public void save(UserDTO user) {
		useRepository.save(user);
	}

	public UserDTO get(Long id) {
		return useRepository.findById(id).get();
	}

	public void delete(Long id) {
		useRepository.deleteById(id);
	}

}
