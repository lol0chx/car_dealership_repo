package com.pluralsight.controllers;

import com.pluralsight.daos.VehicleDao;
import com.pluralsight.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehiclesController {

    private final VehicleDao vehicleDao;

    @Autowired
    public VehiclesController(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    // GET with filters
    @GetMapping
        public ResponseEntity<List<Vehicle>> getVehicles(
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
            @RequestParam(name = "make", required = false) String make,
            @RequestParam(name = "model", required = false) String model,
            @RequestParam(name = "minYear", required = false) Integer minYear,
            @RequestParam(name = "maxYear", required = false) Integer maxYear,
            @RequestParam(name = "color", required = false) String color,
            @RequestParam(name = "minMiles", required = false) Integer minMiles,
            @RequestParam(name = "maxMiles", required = false) Integer maxMiles,
            @RequestParam(name = "type", required = false) String type
        ) {
        List<Vehicle> vehicles = vehicleDao.searchByFilters(minPrice, maxPrice, make, model, minYear, maxYear, color, minMiles, maxMiles, type);
        return ResponseEntity.ok(vehicles);
    }

    // POST add vehicle
    @PostMapping
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle) {
        int rows = vehicleDao.createVehicle(vehicle);
        return ResponseEntity.ok("Inserted: " + rows);
    }

    // PUT update
    @PutMapping("/{vin}")
    public ResponseEntity<String> updateVehicle(@PathVariable int vin, @RequestBody Vehicle vehicle) {
        vehicle.setVin(vin);
        int rows = vehicleDao.updateVehicle(vehicle);
        return ResponseEntity.ok("Updated: " + rows);
    }

    // DELETE by VIN
    @DeleteMapping("/{vin}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int vin) {
        int rows = vehicleDao.deleteByVin(vin);
        return ResponseEntity.ok("Deleted: " + rows);
    }
}
