package com.devsu.llc.microaccountancy.service;

import com.devsu.llc.microaccountancy.dto.request.MovementRequestDto;
import com.devsu.llc.microaccountancy.dto.response.MessageDto;
import com.devsu.llc.microaccountancy.dto.response.MovementDto;

import java.util.List;

public interface MovementService {
    MovementDto create(MovementRequestDto movement);

    MessageDto delete(Integer id);

    List<MovementDto> findAll();

    MovementDto findById(Integer id);
}
