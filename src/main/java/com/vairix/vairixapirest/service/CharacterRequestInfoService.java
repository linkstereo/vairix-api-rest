package com.vairix.vairixapirest.service;

import com.vairix.vairixapirest.model.CharacterRequestInfo;
import com.vairix.vairixapirest.repository.CharacterRequestInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * Servicio que permite guardar info relacionada con los request hechos a los servicios que buscan en los personajes
 * de Marvel.
 */
@Service
@RequiredArgsConstructor
public class CharacterRequestInfoService {

    /**
     * Permite identificar los request.
     */
    public enum RequestType{
        GET_CHARACTERS,
        GET_CHARACTER,
        NOT_SPECIFIED
    }

    private final CharacterRequestInfoRepository repository;

    public void logCharacterRequestInfo(RequestType type){
        CharacterRequestInfo characterRequestInfo = new CharacterRequestInfo();
        characterRequestInfo.setServiceNameRequested(type.name());
        characterRequestInfo.setTimestamp(Timestamp.from(Instant.now()));
        repository.save(characterRequestInfo);
    }
}
