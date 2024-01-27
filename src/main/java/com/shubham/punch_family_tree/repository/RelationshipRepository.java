package com.shubham.punch_family_tree.repository;

import com.shubham.punch_family_tree.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    Relationship findByPerson1IdAndPerson2IdAndType(Long id1, Long id2, String type);

    boolean existsByPerson1IdAndPerson2IdAndType(Long id1, Long id2, String type);

    List<Relationship> findByPerson2IdAndType(Long id, String type);
}
