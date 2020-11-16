package com.uber.uberapi.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "driver")
public class Driver extends Auditable {

    @OneToOne
    private Account account;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String name;

    @OneToOne(mappedBy = "driver")
    private Car car;

    private String licenceDetails;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @Enumerated(EnumType.STRING)
    private DriverApprovalStatus approvalStatus;

    @OneToMany(mappedBy = "driver")
    private List<Booking> bookings;

    private Boolean isAvailable;

    private String activeCity;

    @OneToOne
    private ExactLocation lastKnownLocation;

    @OneToOne
    private ExactLocation home;
}
