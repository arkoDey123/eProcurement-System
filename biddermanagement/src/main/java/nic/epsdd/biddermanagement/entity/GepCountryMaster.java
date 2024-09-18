package nic.epsdd.biddermanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "gep_country_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GepCountryMaster {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "isactive", nullable = false)
    private Boolean isActive = true;

    @Column(name = "createdby", nullable = false)
    private Long createdBy;

    @Column(name = "createddate", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "countrycode", length = 5)
    private String countryCode;

    @Column(name = "phoneisdcode", length = 10, unique = true)
    private String phoneIsdCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdby", insertable = false, updatable = false)
    private GepUser createdByUser;
}
