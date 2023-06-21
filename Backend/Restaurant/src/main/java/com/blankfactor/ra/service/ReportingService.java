package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.ReportingDto;
import com.blankfactor.ra.model.Reporting;

public interface ReportingService {
    Reporting createReport(ReportingDto reportingDto);
}
