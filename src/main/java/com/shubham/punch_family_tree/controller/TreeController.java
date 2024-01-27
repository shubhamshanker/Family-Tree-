package com.shubham.punch_family_tree.controller;

import com.shubham.punch_family_tree.service.TreeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TreeController {

    private final TreeService treeService;

    public String addPerson(String name, String gender){
        return treeService.addPerson(name, gender);
    }

    public String addRelationshipType(String type){
        return treeService.addRelationship(type);
    }

    public String addPersonsAndRelationship(String person1Name, String person2Name, String type){
        return treeService.addPersonsAndRelationship(person1Name, person2Name, type);
    }

}
