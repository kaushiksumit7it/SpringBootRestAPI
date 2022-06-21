package com.dating.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dating.springboot.entity.Interests;

@Repository
public interface InterestsRepository extends JpaRepository<Interests, Long> {

	
}
