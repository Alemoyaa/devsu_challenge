package com.devsu.llc.microaccountancy.controller;

import com.devsu.llc.microaccountancy.dto.request.MovementRequestDto;
import com.devsu.llc.microaccountancy.dto.response.MessageDto;
import com.devsu.llc.microaccountancy.dto.response.MovementDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MovementController {
    ResponseEntity<MovementDto> create(@Valid @RequestBody MovementRequestDto movement);

    ResponseEntity<MessageDto> delete(@PathVariable Integer id);

    ResponseEntity<MovementDto> findById(@PathVariable Integer id);

    ResponseEntity<List<MovementDto>> findAll();

}
