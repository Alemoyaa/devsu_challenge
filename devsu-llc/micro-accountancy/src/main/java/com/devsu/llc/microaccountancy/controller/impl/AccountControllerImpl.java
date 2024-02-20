package com.devsu.llc.microaccountancy.controller.impl;

import com.devsu.llc.microaccountancy.controller.AccountController;
import com.devsu.llc.microaccountancy.dto.response.AccountDto;
import com.devsu.llc.microaccountancy.dto.response.MessageDto;
import com.devsu.llc.microaccountancy.dto.request.AccountRequestDto;
import com.devsu.llc.microaccountancy.dto.request.AccountUpdateRequestDto;
import com.devsu.llc.microaccountancy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    @PostMapping
    public ResponseEntity<AccountDto> create(AccountRequestDto account) {
        return new ResponseEntity<>(this.accountService.create(account), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(Integer id, AccountUpdateRequestDto account) {
        return new ResponseEntity<>(this.accountService.update(id, account), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(Integer id) {
        return new ResponseEntity<>(this.accountService.delete(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> findById(Integer id) {
        return new ResponseEntity<>(this.accountService.findById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<AccountDto>> findAll() {
        return new ResponseEntity<>(this.accountService.findAll(), HttpStatus.OK);
    }

}
