package com.shubham.punch_family_tree.repository;

import com.shubham.punch_family_tree.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByName(String name);

    boolean existsByNameAndGender(String name, String gender);
}
