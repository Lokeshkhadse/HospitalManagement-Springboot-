    package com.example.HospitalManagement.Entity;
    import com.example.HospitalManagement.Entity.Patient;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.Date;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "bills")
    public class Bill {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long billId;

        private String amount;

        @Temporal(TemporalType.DATE)
        private Date billDate;

        private String description;

        @ManyToOne
        @JoinColumn(name = "patient_id")
        private Patient patient;


    }
