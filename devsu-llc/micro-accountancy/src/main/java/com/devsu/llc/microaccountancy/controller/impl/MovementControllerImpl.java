package com.devsu.llc.microaccountancy.controller.impl;

import com.devsu.llc.microaccountancy.controller.MovementController;
import com.devsu.llc.microaccountancy.dto.request.MovementRequestDto;
import com.devsu.llc.microaccountancy.dto.response.MessageDto;
import com.devsu.llc.microaccountancy.dto.response.MovementDto;
import com.devsu.llc.microaccountancy.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovementControllerImpl implements MovementController {

    @Autowired
    private MovementService movementService;

    @Override
    @PostMapping
    public ResponseEntity<MovementDto> create(MovementRequestDto movement) {
        return new ResponseEntity<>(this.movementService.create(movement), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(Integer id) {
        return new ResponseEntity<>(this.movementService.delete(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MovementDto> findById(Integer id) {
        return new ResponseEntity<>(this.movementService.findById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<MovementDto>> findAll() {
        return new ResponseEntity<>(this.movementService.findAll(), HttpStatus.OK);
    }
}
