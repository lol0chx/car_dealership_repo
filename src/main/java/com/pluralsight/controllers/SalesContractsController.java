package com.pluralsight.controllers;

import com.pluralsight.daos.SalesContractDao;
import com.pluralsight.models.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/sales-contracts")
public class SalesContractsController {

    private final SalesContractDao salesContractDao;

    @Autowired
    public SalesContractsController(SalesContractDao salesContractDao) {
        this.salesContractDao = salesContractDao;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesContract> getById(@PathVariable(name = "id") int id) {
        SalesContract sc = salesContractDao.getById(id);
        if (sc == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sc);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestParam(name = "vin") int vin, @RequestParam(name = "date") Date date) {
        int rows = salesContractDao.addSalesContract(vin, date);
        return ResponseEntity.ok("Inserted: " + rows);
    }
}
