package nic.epsdd.biddermanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "gep_lookup_master")
public class GepLookupMaster {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lookuptype", length = 30, nullable = false)
    private String lookupType;

    @Column(name = "lookupcode", length = 30)
    private String lookupCode;

    @Column(name = "lookupdesc", length = 255, nullable = false)
    private String lookupDesc;

    @Column(name = "isactive", nullable = false)
    private Boolean isActive = true;

    @Column(name = "createdby", nullable = false)
    private Long createdBy;

    @Column(name = "createddate", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updatedby")
    private Long updatedBy;

    @Column(name = "updateddate")
    private LocalDateTime updatedDate;

    // Foreign key constraints
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdby", insertable = false, updatable = false)
    private GepUser createdByUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updatedby", insertable = false, updatable = false)
    private GepUser updatedByUser;
}
