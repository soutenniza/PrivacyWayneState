package com.springapp.mvc.repository;

import com.springapp.mvc.model.Comment;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Zachary on 3/28/2015.
 */
@Repository
public interface CommentRepository extends GraphRepository<Comment> {
}
