package com.vairix.vairixapirest.service;

import com.vairix.marveladapter.client.MarvelClient;
import com.vairix.marveladapter.model.Character;
import com.vairix.vairixapirest.exception.CharacterNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarvelService {

    private final MarvelClient marvelClient;

    public List<Character> getCharacters(){
        var results = marvelClient.getCharacters();

        if (results.getData().getCount() > 0) {
            return results.getData().getResults();
        }

        return List.of();
    }

    public Character getCharacter(String characerId) throws CharacterNotExistException {
        var results = marvelClient.getCharacter(characerId);

        if (results.getData().getCount() > 0) {
            return results.getData().getResults().get(0);
        } else {
            throw new CharacterNotExistException();
        }


    }
}
