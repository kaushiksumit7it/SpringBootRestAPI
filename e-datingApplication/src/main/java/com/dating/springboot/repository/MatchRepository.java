package com.dating.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.dating.springboot.entity.Match;
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

	public List<Match> findByUserId(Long userId);

	public List<Match> findByUserIdOrMatchedUserId(Long userId, Long matchedUserId);
}
