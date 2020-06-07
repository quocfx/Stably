package com.stably.shortproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stably.shortproject.model.MyURL;

@Repository
public interface MyURLRepository extends CrudRepository<MyURL, Integer>  {
}
