package com.example.HospitalManagement.controller;



import com.example.HospitalManagement.CommonResponse.Response;
import com.example.HospitalManagement.dto.RoomDto;
import com.example.HospitalManagement.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;


    @PostMapping
    public ResponseEntity<Response<RoomDto>> createRoom(@RequestBody RoomDto roomDto) {
        RoomDto createdRoom = roomService.createRoom(roomDto);
        Response<RoomDto> response = Response.successfulResponse("Room created successfully", createdRoom);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<RoomDto>> getRoomById(@PathVariable("id") Long id) {
        RoomDto roomDto = roomService.getRoomById(id);
        Response<RoomDto> response = Response.successfulResponse("Room fetched successfully", roomDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<RoomDto>>> getAllRooms() {
        List<RoomDto> rooms = roomService.getAllRooms();
        Response<List<RoomDto>> response = Response.successfulResponse("All rooms fetched successfully", rooms);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<RoomDto>> updateRoom(@PathVariable("id") Long id, @RequestBody RoomDto roomDto) {
        RoomDto updatedRoom = roomService.updateRoom(id, roomDto);
        Response<RoomDto> response = Response.successfulResponse("Room updated successfully", updatedRoom);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteRoom(@PathVariable("id") Long id) {
        roomService.deleteRoom(id);
        Response<Void> response = Response.successfulResponse("Room deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
