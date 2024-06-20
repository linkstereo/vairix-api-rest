package com.vairix.vairixapirest.controller;

import com.vairix.marveladapter.model.Character;
import com.vairix.vairixapirest.model.CharacterRequestInfo;
import com.vairix.vairixapirest.service.CharacterRequestInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.vairix.vairixapirest.controller.ControllerConstants.*;

/**
 * Permite acceder a informacion de los request hechos desde Frontend.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/"+BASE_API_VERSION+"/")
public class RequestsInfoController {

    private final CharacterRequestInfoService service;

    /**
     * Obtiene un listado de los requests.
     *
     * @return List of {@link Character}
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = REQUEST_URI_BASE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CharacterRequestInfo> getRequestInfo(){
        return service.getAll();
    }
}
