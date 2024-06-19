package com.vairix.vairixapirest.controller;

import com.vairix.marveladapter.model.Character;
import com.vairix.vairixapirest.exception.CharacterNotExistException;
import com.vairix.vairixapirest.service.CharacterRequestInfoService;
import com.vairix.vairixapirest.service.MarvelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static com.vairix.vairixapirest.controller.ControllerConstants.*;
import static com.vairix.vairixapirest.service.CharacterRequestInfoService.RequestType.GET_CHARACTER;
import static com.vairix.vairixapirest.service.CharacterRequestInfoService.RequestType.GET_CHARACTERS;

/**
 * Permite acceder a informacion de los personajes de Marvel desde Frontend.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/"+BASE_API_VERSION+"/")
public class CharacterRestController {

    private final MarvelService marvelService;
    private final CharacterRequestInfoService characterRequestInfoService;

    /**
     * Obtiene toda las lista de Personajes.
     * Persiste informacion de hora y request hecho.
     * @return List of {@link Character}
     */
    @GetMapping(CHARACTERS_URI_BASE)
    public List<Character> getCharacters(){
        characterRequestInfoService.logCharacterRequestInfo(GET_CHARACTERS);
        return marvelService.getCharacters();
    }

    /**
     * Obtiene un personaje segun su characterId
     * Persiste informacion de hora y request hecho.
     * @param characterId - Identificador del personaje
     * @return {@link Character} - Personaje encontrado
     * @throws CharacterNotExistException
     */
    @GetMapping(CHARACTERS_URI_BASE + "/{characterId}")
    public Character getCharacter(@PathVariable("characterId") String characterId) throws CharacterNotExistException {
        characterRequestInfoService.logCharacterRequestInfo(GET_CHARACTER);
        return marvelService.getCharacter(characterId);
    }


}
