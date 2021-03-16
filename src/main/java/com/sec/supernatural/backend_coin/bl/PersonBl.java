package com.sec.supernatural.backend_coin.bl;

import com.sec.supernatural.backend_coin.po.Person;
import com.sec.supernatural.backend_coin.vo.Node;

import java.util.List;


public interface PersonBl {
    Person findByName(String name);
    List<Node> findAll();
}
