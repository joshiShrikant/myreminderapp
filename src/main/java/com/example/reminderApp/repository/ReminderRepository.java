package com.example.reminderApp.repository;

import com.example.reminderApp.entity.Reminder;
import com.example.reminderApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

}