package com.example.HospitalManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private Long roomId;
    private String roomNumber;
    private String type;
    private boolean available;
    private List<Long> patientIds;  // List of patient IDs in the room
}
