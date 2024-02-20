package com.devsu.llc.microaccountancy.controller.impl;

import com.devsu.llc.microaccountancy.controller.ReportController;
import com.devsu.llc.microaccountancy.dto.response.ReportDto;
import com.devsu.llc.microaccountancy.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reportes")
public class ReportControllerImpl implements ReportController {

    @Autowired
    private ReportService reportService;

    @Override
    @GetMapping
    public ResponseEntity<ReportDto> generateReport(LocalDate startDate, LocalDate endDate, Long identification) {
        return new ResponseEntity<>(reportService.generateReport(startDate, endDate, identification), HttpStatus.OK);
    }
}
