package com.example.reminderApp.controller;

import com.example.reminderApp.entity.Reminder;
import com.example.reminderApp.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reminder")
@CrossOrigin(origins = "http://localhost:4200") // for Angular
public class ReminderController {

    @Autowired
    private ReminderService service;

    @GetMapping
    public List<Reminder> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Reminder save(@RequestBody Reminder reminder) {
        return service.save(reminder);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reminder> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
