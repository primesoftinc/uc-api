package com.prime.service;

import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class UserService {

    @GraphQLQuery(name = "greeting")
    public String greeting() {
        return "hello world";
    }
}
