package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.Room;
import com.example.HospitalManagement.Repository.RoomRepository;
import com.example.HospitalManagement.dto.RoomDto;
import com.example.HospitalManagement.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public RoomDto createRoom(RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        room = roomRepository.save(room);
        return roomMapper.toDto(room);
    }

    @Override
    public RoomDto updateRoom(Long id, RoomDto roomDto) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));

        roomMapper.updateEntityFromDTO(roomDto, room);
        // Handle patient relationships if needed
        room = roomRepository.save(room);
        return roomMapper.toDto(room);
    }

    @Override
    public RoomDto getRoomById(Long id) {
        return roomRepository.findById(id)
                .map(roomMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));
    }

    @Override
    public List<RoomDto> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(roomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
        }
        roomRepository.deleteById(id);
    }
}
