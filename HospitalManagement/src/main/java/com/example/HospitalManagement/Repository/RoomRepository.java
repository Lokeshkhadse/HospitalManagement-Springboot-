package com.example.HospitalManagement.Repository;

import com.example.HospitalManagement.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
