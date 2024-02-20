package com.devsu.llc.microaccountancy.controller;

import com.devsu.llc.microaccountancy.dto.response.AccountDto;
import com.devsu.llc.microaccountancy.dto.response.MessageDto;
import com.devsu.llc.microaccountancy.dto.request.AccountRequestDto;
import com.devsu.llc.microaccountancy.dto.request.AccountUpdateRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AccountController {
    ResponseEntity<AccountDto> create(@Valid @RequestBody AccountRequestDto account);
    ResponseEntity<AccountDto> update(@PathVariable Integer id, @Valid @RequestBody AccountUpdateRequestDto account);
    ResponseEntity<MessageDto> delete(@PathVariable Integer id);
    ResponseEntity<AccountDto> findById(@PathVariable Integer id);
    ResponseEntity<List<AccountDto>> findAll();
}
