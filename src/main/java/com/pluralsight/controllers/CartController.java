package com.pluralsight.controllers;

import com.pluralsight.daos.CartDao;
import com.pluralsight.models.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartDao cartDao;

    public CartController(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> viewCart() {
        List<Cart> items = cartDao.viewCart();
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestParam(name = "vin") int vin) {
        int id = cartDao.addCart(vin);
        return ResponseEntity.ok("added cart item id=" + id);
    }

    @DeleteMapping("/{vin}")
    public ResponseEntity<Void> removeFromCart(@PathVariable(name = "vin") int vin) {
        cartDao.deleteCart(vin);
        return ResponseEntity.noContent().build();
    }
}
