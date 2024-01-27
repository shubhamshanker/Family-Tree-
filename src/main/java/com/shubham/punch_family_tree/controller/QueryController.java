package com.shubham.punch_family_tree.controller;

import com.shubham.punch_family_tree.service.QueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QueryController {

    private final QueryService queryService;

    public Integer countOfSons(String name) {
        return queryService.countOfSons(name);
    }

    public Integer countOfDaughters(String name) {
        return queryService.countOfDaughters(name);
    }

    public Integer countOfWives(String name) {
        return queryService.countOfWives(name);
    }

    public String fatherName(String name) {
        String fatherName = queryService.fatherName(name);
        return fatherName.isEmpty() ? "" : fatherName;
    }

}
