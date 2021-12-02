package com.apress.AWS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apress.AWS.dto.UserDTO;

@Repository
public interface UserJpaRepository extends JpaRepository<UserDTO, Long> {

}
