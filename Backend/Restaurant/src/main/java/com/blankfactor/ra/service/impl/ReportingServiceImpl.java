package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.ReportingDto;
import com.blankfactor.ra.exceptions.custom.AppTableException;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Reporting;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.ReportingRepository;
import com.blankfactor.ra.service.ReportingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReportingServiceImpl implements ReportingService {
    private final ReportingRepository reportingRepository;
    private final AppTableRepository appTableRepository;

    @Override
    public Reporting createReport(ReportingDto reportingDto) {
        AppTable appTable = appTableRepository.findById(reportingDto.getTable().getId())
                .orElseThrow(() -> new AppTableException("No table with id " + reportingDto.getTable().getId()));

        if (!appTable.isOccupied()) {
            throw new AppTableException("App table is not occupied");
        }

        Reporting reporting = Reporting.builder()
                .table(reportingDto.getTable())
                .restaurant(reportingDto.getRestaurant())
                .reportFrom(reportingDto.getReportFrom())
                .reportTo(reportingDto.getReportTo())
                .build();

        return reportingRepository.save(reporting);
    }
}
