package com.thomascook.integration.poc.contract.testing.provider.personalinfo.repository;

import com.thomascook.integration.poc.contract.testing.provider.personalinfo.model.Character;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CharacterRepository {

    private static final Map<String, Character> DB;

    static {
        DB = new HashMap<>();
        DB.put("luke", Character.builder()
                .name("Luke Skywalker")
                .height("172")
                .mass("77")
                .build());
    }

    public Optional<Character> getById(String id) {
        return Optional.ofNullable(DB.get(id));
    }

}
