package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.Doctor;
import com.example.HospitalManagement.Exception.ResourceNotFoundException;
import com.example.HospitalManagement.Repository.DoctorRepository;
import com.example.HospitalManagement.dto.DoctorDto;
import com.example.HospitalManagement.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

 @Autowired
  private DoctorRepository doctorRepository;

    @Autowired
    private DoctorMapper doctorMapper;

//    @Override
//    public Doctor createDoctor(Doctor doctor) {
//        return doctorRepository.save(doctor);
//    }
@Override
public DoctorDto createDoctor(DoctorDto doctorDto) {
    Doctor doctor = doctorMapper.toEntity(doctorDto);
    Doctor savedDoctor = doctorRepository.save(doctor);
    return doctorMapper.toDto(savedDoctor);
}
//
//    @Override
//    public Doctor getDoctorById(Long doctor_id) {
//        return doctorRepository.findById(doctor_id).orElseThrow(() -> new ResourceNotFoundException("doctor not found"));
//    }


    @Override
    public DoctorDto getDoctorById(Long doctor_id) {
        Doctor doctor = doctorRepository.findById(doctor_id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctor_id));
        return doctorMapper.toDto(doctor);
    }


//    @Override
//    public List<Doctor> getAllDoctors() {
//        return doctorRepository.findAll();
//    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public Doctor updateDoctor(Long doctor_id, Doctor doctor) {
//        if(doctorRepository.existsById(doctor_id)){
//            doctor.setDoctor_id(doctor_id);
//            return doctorRepository.save(doctor);
//        }
//        return null;
//    }

    @Override
    public DoctorDto updateDoctor(Long doctor_id, DoctorDto doctorDto) {
        Doctor doctor = doctorRepository.findById(doctor_id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctor_id));
        doctorMapper.updateEntityFromDto(doctorDto, doctor);
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDto(updatedDoctor);
    }

//    @Override
//    public void deleteDoctor(Long doctor_id) {
//        doctorRepository.deleteById(doctor_id);
//    }
@Override
public void deleteDoctor(Long doctor_id) {
    if (!doctorRepository.existsById(doctor_id)) {
        throw new ResourceNotFoundException("Doctor not found with id: " + doctor_id);
    }
    doctorRepository.deleteById(doctor_id);
}
}
