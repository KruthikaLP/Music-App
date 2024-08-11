package com.example.Tunehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tunehub.entities.PlayList;

public interface PlayRepository extends JpaRepository<PlayList, Integer> {

}
