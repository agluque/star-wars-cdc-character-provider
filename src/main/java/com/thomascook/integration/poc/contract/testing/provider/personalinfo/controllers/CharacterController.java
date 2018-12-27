package com.thomascook.integration.poc.contract.testing.provider.personalinfo.controllers;

import com.thomascook.integration.poc.contract.testing.provider.personalinfo.model.Character;
import com.thomascook.integration.poc.contract.testing.provider.personalinfo.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterRepository characterRepository;

    @GetMapping("/characters")
    public ResponseEntity<Character> character(@RequestParam(value = "id") String id) {
        return characterRepository.getById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
