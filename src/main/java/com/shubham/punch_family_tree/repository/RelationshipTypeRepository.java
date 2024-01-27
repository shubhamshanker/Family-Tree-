package com.shubham.punch_family_tree.repository;

import com.shubham.punch_family_tree.model.RelationshipType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationshipTypeRepository extends JpaRepository<RelationshipType, Long> {
    boolean existsByType(String type);

    RelationshipType findByType(String type);
}
