package com.example.reminderApp.service;

import com.example.reminderApp.Repository.ReminderRepository;
import com.example.reminderApp.entity.Reminder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {
    @Autowired
    private ReminderRepository repository;

    public List<Reminder> getAll() {
        return repository.findAll();
    }

    public Reminder save(Reminder reminder) {
        return repository.save(reminder);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Reminder> findById(Long id) {
        return repository.findById(id);
    }
}
