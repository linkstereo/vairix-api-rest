package com.vairix.vairixapirest.service;

import com.vairix.vairixapirest.model.CharacterRequestInfo;
import com.vairix.vairixapirest.repository.CharacterRequestInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CharacterRequestInfoService {

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
