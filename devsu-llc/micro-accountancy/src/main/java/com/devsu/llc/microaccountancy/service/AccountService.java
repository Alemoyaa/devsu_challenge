package com.devsu.llc.microaccountancy.service;

import com.devsu.llc.microaccountancy.dto.response.AccountDto;
import com.devsu.llc.microaccountancy.dto.response.MessageDto;
import com.devsu.llc.microaccountancy.dto.request.AccountRequestDto;
import com.devsu.llc.microaccountancy.dto.request.AccountUpdateRequestDto;

import java.util.List;

public interface AccountService {
    AccountDto create(AccountRequestDto account);
    AccountDto update(Integer id, AccountUpdateRequestDto account);
    MessageDto delete(Integer id);
    List<AccountDto> findAll();
    AccountDto findById(Integer id);
}
