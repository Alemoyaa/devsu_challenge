package com.devsu.llc.microaccountancy.service.impl;

import com.devsu.llc.microaccountancy.client.CustomerClient;
import com.devsu.llc.microaccountancy.dto.http.CustomerDto;
import com.devsu.llc.microaccountancy.dto.response.AccountExtendDto;
import com.devsu.llc.microaccountancy.dto.response.MovementDto;
import com.devsu.llc.microaccountancy.dto.response.ReportDto;
import com.devsu.llc.microaccountancy.exception.ResourceNotFoundException;
import com.devsu.llc.microaccountancy.model.Account;
import com.devsu.llc.microaccountancy.model.Movement;
import com.devsu.llc.microaccountancy.repository.AccountRepository;
import com.devsu.llc.microaccountancy.repository.MovementRepository;
import com.devsu.llc.microaccountancy.service.ReportService;
import com.devsu.llc.microaccountancy.util.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private CustomerClient customerClient;

    @Override
    public ReportDto generateReport(LocalDate startDate, LocalDate endDate, Long identification) {
        CustomerDto customerDto = this.customerClient.getCustomerById(identification).orElseThrow(() -> new ResourceNotFoundException("El cliente no existe."));

        List<Account> accounts = this.accountRepository.findByIdentification(identification);
        if (accounts.isEmpty()) {
            throw new ResourceNotFoundException("El cliente no tiene cuentas.");
        }

        ReportDto reportDto = new ReportDto();
        reportDto.setCustomer(customerDto);
        reportDto.setAccounts(new HashMap<>());

        accounts.forEach(account -> {
            List<Movement> movementList = this.movementRepository.findByDateBetweenAndIdentification(startDate.atStartOfDay(), endDate.atStartOfDay(), account.getNumberAccount());
            reportDto.getAccounts().put(MapperService.map(account, AccountExtendDto.class), MapperService.mapAll(movementList, MovementDto.class));
        });

        return reportDto;
    }
}
