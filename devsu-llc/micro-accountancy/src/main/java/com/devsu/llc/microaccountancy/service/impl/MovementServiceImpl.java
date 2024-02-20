package com.devsu.llc.microaccountancy.service.impl;

import com.devsu.llc.microaccountancy.dto.request.MovementRequestDto;
import com.devsu.llc.microaccountancy.dto.response.MessageDto;
import com.devsu.llc.microaccountancy.dto.response.MovementDto;
import com.devsu.llc.microaccountancy.enums.State;
import com.devsu.llc.microaccountancy.enums.TypeMovement;
import com.devsu.llc.microaccountancy.exception.ResourceBadRequestException;
import com.devsu.llc.microaccountancy.exception.ResourceNotEnoughAmountException;
import com.devsu.llc.microaccountancy.exception.ResourceNotFoundException;
import com.devsu.llc.microaccountancy.model.Account;
import com.devsu.llc.microaccountancy.model.Movement;
import com.devsu.llc.microaccountancy.repository.AccountRepository;
import com.devsu.llc.microaccountancy.repository.MovementRepository;
import com.devsu.llc.microaccountancy.service.MovementService;
import com.devsu.llc.microaccountancy.util.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {

    private static final String MOVEMENT_NOT_FOUND = "El movimiento no existe.";

    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public MovementDto create(MovementRequestDto dto) {
        Account accountFind = accountRepository.findById(dto.getNumberAccount()).orElseThrow(() -> new ResourceNotFoundException("La cuenta no existe."));
        if (accountFind.getState() == State.INACTIVE) {
            throw new ResourceBadRequestException("La cuenta se encuentra inactiva.");
        }

        Movement model = MapperService.map(dto, Movement.class);
        Double newAmount = validateMovement(accountFind.getAmountInitial(), dto.getValue(), dto.getTypeMovement());

        model.setAmount(newAmount);
        accountFind.setAmountInitial(newAmount);

        accountRepository.save(accountFind);

        model.setAccount(accountFind);
        model.setDate(new java.util.Date());

        return MapperService.map(movementRepository.save(model), MovementDto.class);
    }

    private double validateMovement(Double amount, Double value, TypeMovement typeMovement) {
        boolean isExpense = typeMovement == TypeMovement.EGRESO;
        boolean isNegativeValue = value < 0;

        if ((isExpense && value > 0) || (!isExpense && isNegativeValue)) {
            if (amount < Math.abs(value)) {
                throw new ResourceNotEnoughAmountException("Saldo no disponible.");
            }
        }

        return isExpense ? amount - value : amount + value;

    }

    @Override
    public MessageDto delete(Integer id) {
        movementRepository.findById(id).ifPresentOrElse(
                c -> movementRepository.delete(c),
                () -> {
                    throw new ResourceNotFoundException(MOVEMENT_NOT_FOUND);
                });
        return new MessageDto("Movimiento eliminado con éxito.");
    }

    @Override
    public List<MovementDto> findAll() {
        return MapperService.mapAll(movementRepository.findAll(), MovementDto.class);
    }

    @Override
    public MovementDto findById(Integer id) {
        return MapperService.map(movementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MOVEMENT_NOT_FOUND)), MovementDto.class);
    }
}
