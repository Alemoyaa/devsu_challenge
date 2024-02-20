package com.devsu.llc.microaccountancy.service;

import com.devsu.llc.microaccountancy.dto.response.ReportDto;

import java.time.LocalDate;

public interface ReportService {
    ReportDto generateReport(LocalDate startDate, LocalDate endDate, Long identification);

}
