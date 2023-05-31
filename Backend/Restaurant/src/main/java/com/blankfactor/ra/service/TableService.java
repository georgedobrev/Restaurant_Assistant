package com.blankfactor.ra.service;


import com.blankfactor.ra.entity.Table;
import com.blankfactor.ra.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {
    private final TableRepository tableRepository;

    @Autowired
    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public Table createTable(Table table) {
        return tableRepository.save(table);
    }

    public Table getTableById(Integer tableId) {
        return tableRepository.findById(tableId).orElse(null);
    }
}

