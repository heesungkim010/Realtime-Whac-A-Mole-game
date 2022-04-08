package com.game.whac_a_mole.repository;

import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class InMemoryDB {
    private Map<String, Integer> memberDB = new ConcurrentHashMap<String, Integer>();

}
