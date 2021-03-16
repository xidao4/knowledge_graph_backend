package com.sec.supernatural.backend_coin.mapper;

import com.sec.supernatural.backend_coin.po.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonDao extends Neo4jRepository<Person,Long> {
    Person findByName(String name);
}
