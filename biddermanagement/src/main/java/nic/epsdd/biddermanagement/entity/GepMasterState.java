package nic.epsdd.biddermanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gep_master_state", uniqueConstraints = {
        @UniqueConstraint(columnNames = "statecode"),
        @UniqueConstraint(columnNames = "statename")
})
public class GepMasterState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "statename", length = 150, nullable = false)
    private String stateName;

    @Column(name = "statecode", length = 5, nullable = false)
    private String stateCode;

    @Column(name = "statedesc", length = 255, nullable = false)
    private String stateDescription;

    @Column(name = "countryid")
    private Long countryId;

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

    @Column(name = "gstnstatecode", length = 5, nullable = false)
    private String gstnStateCode;

}