package com.devsu.llc.microaccountancy.service.impl;

import com.devsu.llc.microaccountancy.client.CustomerClient;
import com.devsu.llc.microaccountancy.dto.response.AccountDto;
import com.devsu.llc.microaccountancy.dto.http.CustomerDto;
import com.devsu.llc.microaccountancy.dto.response.MessageDto;
import com.devsu.llc.microaccountancy.dto.request.AccountRequestDto;
import com.devsu.llc.microaccountancy.dto.request.AccountUpdateRequestDto;
import com.devsu.llc.microaccountancy.enums.State;
import com.devsu.llc.microaccountancy.exception.ResourceBadRequestException;
import com.devsu.llc.microaccountancy.exception.ResourceNotFoundException;
import com.devsu.llc.microaccountancy.model.Account;
import com.devsu.llc.microaccountancy.repository.AccountRepository;
import com.devsu.llc.microaccountancy.service.AccountService;
import com.devsu.llc.microaccountancy.util.MapperService;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private static final String ACCOUNT_NOT_FOUND = "La cuenta no existe.";
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerClient customerClient;

    @Override
    public AccountDto create(AccountRequestDto dto) {
        CustomerDto customerDto = customerClient.getCustomerById(dto.getIdentification()).orElseThrow(() -> new BadRequestException("El cliente no existe."));
        Account account = MapperService.map(dto, Account.class);
        account.setState(State.ACTIVE);
        account.setNumberAccount(customerDto.getCustomerId());
        return MapperService.map(accountRepository.save(account), AccountDto.class);
    }

    @Override
    public AccountDto update(Integer id, AccountUpdateRequestDto dto) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ACCOUNT_NOT_FOUND));
        if (dto.getAmountInitial() != null)
            account.setAmountInitial(dto.getAmountInitial());
        if (dto.getTypeAccount() != null)
            account.setTypeAccount(dto.getTypeAccount());
        return MapperService.map(accountRepository.save(account), AccountDto.class);
    }

    @Override
    public MessageDto delete(Integer id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ACCOUNT_NOT_FOUND));
        if (account.getState().equals(State.INACTIVE))
            throw new ResourceBadRequestException("La cuenta ya ha sido eliminada.");
        account.setState(State.INACTIVE);
        accountRepository.save(account);
        return new MessageDto("Cuenta eliminada con éxito.");
    }

    @Override
    public List<AccountDto> findAll() {
        return MapperService.mapAll(accountRepository.findAll(), AccountDto.class);
    }

    @Override
    public AccountDto findById(Integer id) {
        return MapperService.map(accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ACCOUNT_NOT_FOUND)), AccountDto.class);
    }

}
