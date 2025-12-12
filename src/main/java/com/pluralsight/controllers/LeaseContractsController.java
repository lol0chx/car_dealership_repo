package com.pluralsight.controllers;

import com.pluralsight.daos.LeaseContractDao;
import com.pluralsight.models.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/lease-contracts")
public class LeaseContractsController {

    private final LeaseContractDao leaseContractDao;

    @Autowired
    public LeaseContractsController(LeaseContractDao leaseContractDao) {
        this.leaseContractDao = leaseContractDao;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaseContract> getById(@PathVariable(name = "id") int id) {
        LeaseContract lc = leaseContractDao.getById(id);
        if (lc == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(lc);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestParam(name = "vin") int vin, @RequestParam(name = "date") Date date) {
        int rows = leaseContractDao.addLeaseContract(vin, date);
        return ResponseEntity.ok("Inserted: " + rows);
    }
}
