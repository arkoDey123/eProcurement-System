package nic.epsdd.biddermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "gep_bidder_privilege_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GepBidderPrivilegeMaster {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "privilegename", nullable = false, unique = true, length = 50)
    private String privilegeName;

    @Column(name = "privilegedesc", nullable = false, length = 255)
    private String privilegeDesc;

    @Column(name = "tolerancepercentage")
    private Double tolerancePercentage;

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

    @Column(name = "pppercentage")
    private Long ppPercentage;
}
