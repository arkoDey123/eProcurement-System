package nic.epsdd.biddermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

@Entity
@Table(name = "gep_bidder_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GepBidderCategory {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "biddercategoryname", nullable = false, length = 150)
    private String bidderCategoryName;

    @Column(name = "isactive", nullable = false)
    private Boolean isActive = true;

    @Column(name = "createdby", nullable = false)
    private Long createdBy;

    @Column(name = "createddate", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updatedby", nullable = false)
    private Long updatedBy;

    @Column(name = "updateddate", nullable = false)
    private LocalDateTime updatedDate;
}
