package com.sec.supernatural.backend_coin.blImpl;

import com.sec.supernatural.backend_coin.bl.PersonBl;
import com.sec.supernatural.backend_coin.po.Person;
import com.sec.supernatural.backend_coin.mapper.PersonDao;
import com.sec.supernatural.backend_coin.vo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonBlImpl implements PersonBl {
    @Autowired
    PersonDao personDao;
    @Override
    public Person findByName(String name){
        return personDao.findByName(name);
    }

    @Override
    public List<Node> findAll() {
        Iterable<Person> people=personDao.findAll();
        List<Node> nodes=new ArrayList<>();
        for(Person p:people){
            nodes.add(new Node(p.getName(),1));//group暂时先写为1
        }
        return nodes;
    }
}
