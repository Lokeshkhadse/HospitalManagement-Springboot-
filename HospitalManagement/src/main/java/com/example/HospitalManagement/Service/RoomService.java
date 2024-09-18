package com.example.HospitalManagement.Service;


import com.example.HospitalManagement.dto.RoomDto;
import java.util.List;

public interface RoomService {
    RoomDto createRoom(RoomDto roomDto);
    RoomDto updateRoom(Long id, RoomDto roomDto);
    RoomDto getRoomById(Long id);
    List<RoomDto> getAllRooms();
    void deleteRoom(Long id);
}
