package com.shubham.punch_family_tree.service;

import com.shubham.punch_family_tree.model.Person;
import com.shubham.punch_family_tree.model.Relationship;
import com.shubham.punch_family_tree.repository.PersonRepository;
import com.shubham.punch_family_tree.repository.RelationshipRepository;
import com.shubham.punch_family_tree.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QueryService {
    private final PersonRepository personRepository;
    private final RelationshipRepository relationshipRepository;

    public Integer countOfSons(String name)
    {
        Person person = personRepository.findByName(name);
        if(Objects.isNull(person)) return 0;
        List<Relationship> sons = relationshipRepository.findByPerson2IdAndType(person.getId(), Constants.SON);
        return sons.size();
    }

    public Integer countOfDaughters(String name)
    {
        Person person = personRepository.findByName(name);
        if(Objects.isNull(person)) return 0;
        List<Relationship> daughters = relationshipRepository.findByPerson2IdAndType(person.getId(), Constants.DAUGHTER);
        return daughters.size();
    }

    public Integer countOfWives(String name)
    {
        Person person = personRepository.findByName(name);
        if(Objects.isNull(person)) return 0;
        List<Relationship> wives = relationshipRepository.findByPerson2IdAndType(person.getId(), Constants.WIFE);
        return wives.size();
    }

    public String fatherName(String name)
    {
        Person person = personRepository.findByName(name);
        if(Objects.isNull(person)) return "";
        List<Relationship> sons = relationshipRepository.findByPerson2IdAndType(person.getId(), Constants.FATHER);
        return personRepository.findById(sons.get(0).getPerson1Id()).get().getName() + "\n";
    }
}
