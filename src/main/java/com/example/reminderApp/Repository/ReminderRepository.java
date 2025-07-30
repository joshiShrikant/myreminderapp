package com.example.reminderApp.Repository;

import com.example.reminderApp.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    // Custom query methods if needed
}
