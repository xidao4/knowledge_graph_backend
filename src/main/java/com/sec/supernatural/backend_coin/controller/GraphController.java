package com.sec.supernatural.backend_coin.controller;

import com.sec.supernatural.backend_coin.bl.GraphService;
import com.sec.supernatural.backend_coin.po.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/graph")
public class GraphController {
    @Autowired
    GraphService graphService;

}
