package com.shubham.punch_family_tree.service;

import com.shubham.punch_family_tree.model.Person;
import com.shubham.punch_family_tree.model.Relationship;
import com.shubham.punch_family_tree.model.RelationshipType;
import com.shubham.punch_family_tree.repository.PersonRepository;
import com.shubham.punch_family_tree.repository.RelationshipRepository;
import com.shubham.punch_family_tree.repository.RelationshipTypeRepository;
import com.shubham.punch_family_tree.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TreeService {

    private final PersonRepository personRepository;
    private final RelationshipTypeRepository relationshipTypeRepository;
    private final RelationshipRepository relationshipRepository;

    private static final Map<String, String> inverseRelationships = Map.of(
            Constants.SON, Constants.FATHER,
            Constants.FATHER, Constants.SON,
            Constants.HUSBAND, Constants.WIFE,
            Constants.WIFE, Constants.HUSBAND,
            Constants.BROTHER, Constants.SISTER,
            Constants.SISTER, Constants.BROTHER
            // other relationships to be added if needed
    );

    public String addPerson(String name, String gender)
    {
        if(checkIfExists(name, gender)) return Constants.ALREADY_EXISTS;
        else {
            Person newPerson = Person.builder().name(name).gender(gender).build();
            personRepository.save(newPerson);
            log.info("Saved person {}", newPerson);
            return "Saved " + name + " successfully" + "\n";
        }
    }

    private boolean checkIfExists(String name, String gender) {
        return personRepository.existsByNameAndGender(name, gender);
    }

    private boolean checkIfExists(String type) {
        return relationshipTypeRepository.existsByType(type);
    }

    public String addRelationship(String type)
    {
        if(checkIfExists(type)) return Constants.ALREADY_EXISTS;
        else {
            RelationshipType relationshipType = RelationshipType.builder().type(type).build();
            relationshipTypeRepository.save(relationshipType);
            log.info("Saved relationship {}", relationshipType);
            return "Saved " + type + " successfully" + "\n";
        }
    }

    private boolean checkIfExists(Person person1, Person person2, RelationshipType relationshipType)
    {
        return (relationshipRepository.existsByPerson1IdAndPerson2IdAndType(person1.getId(), person2.getId(), relationshipType.getType())
        && !Objects.equals(relationshipRepository.findByPerson1IdAndPerson2IdAndType(person1.getId(), person2.getId(), relationshipType.getType()).getType(), relationshipType.getType()));
    }

    public String addPersonsAndRelationship(String person1Name, String person2Name, String type)
    {
        Person person1 = personRepository.findByName(person1Name);
        Person person2 = personRepository.findByName(person2Name);
        RelationshipType relationshipType = relationshipTypeRepository.findByType(type);

        if(Objects.isNull(relationshipType)) return "This relationship has not been added yet";
        if(checkIfExists(person1, person2, relationshipType)) return "Please remove already existent relationship";

        // gender relation checks if needed
        // inverse mappings
        inverseRelationships.forEach((key, value) -> {
            if(relationshipType.getType().equals(key)) {
            Relationship fatherRelationship = Relationship.builder().person1Id(person2.getId()).person2Id(person1.getId()).type(value).build();
            relationshipRepository.save(fatherRelationship);
        }});

        Relationship relationship = Relationship.builder().person1Id(person1.getId()).person2Id(person2.getId()).type(relationshipType.getType()).build();
        relationshipRepository.save(relationship);

        log.info("Saved relationship {}", relationship);
        return "Saved relationship successfully" + "\n";
    }

//    public String removePersonAndRelationship(String person1Name, String person2Name, String type)
//    {
//        Person person1 = personRepository.findByName(person1Name);
//        Person person2 = personRepository.findByName(person2Name);
//        RelationshipType relationshipType = relationshipTypeRepository.findByType(type);
//
//        if(checkIfExists(person1, person2, relationshipType))
//        {
//            Relationship relationship = relationshipRepository.findByPerson1IdAndPerson2IdAndType(person1.getId(), person2.getId(), relationshipType.getType());
//            String inverseType = inverseRelationships.get(relationshipType.getType());
//            Relationship inverseRelationship = relationshipRepository.findByPerson1IdAndPerson2IdAndType(person2.getId(), person1.getId(), inverseType);
//            relationshipRepository.deleteById(relationship.getId());
//            relationshipRepository.deleteById(inverseRelationship.getId());
//            log.info("Deleted both relationships");
//            return "Deleted relationships : " + relationship.getId() + ", " + inverseRelationship.getId();
//        }
//        else {
//            return "No relationship found.";
//        }
//    }



}
