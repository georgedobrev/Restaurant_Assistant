package com.blankfactor.ra.controller;


import com.blankfactor.ra.model.Table;
import com.blankfactor.ra.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/table")
public class TableController {
    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/create")
    public ResponseEntity<Table> createTable(@RequestBody Table table) {
        Table createdTable = tableService.createTable(table);
        return ResponseEntity.ok(createdTable);
    }

    @GetMapping("/{tableId}")
    public ResponseEntity<Table> getTableById(@PathVariable String tableName) {

        Table table = tableService.getTableByName(tableName);
        if (table != null) {
            return ResponseEntity.ok(table);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

