package nic.epsdd.biddermanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "gep_corporate_tenderer")
public class GepCorporateTenderer {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "company", length = 250, nullable = false)
    private String company;

    @Column(name = "registeredaddress", length = 250)
    private String registeredAddress;

    @Column(name = "corporateaddress", length = 250)
    private String corporateAddress;

    @Column(name = "establishedyear")
    private Integer establishedYear;

    @Column(name = "businessnature", length = 50)
    private String businessNature;

    @Column(name = "legalstatus", length = 30)
    private String legalStatus;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "contactname", length = 250)
    private String contactName;

    @Column(name = "designation", length = 50)
    private String designation;

    @Column(name = "phoneisdcodeid")
    private Long phoneIsdCodeId;

    @Column(name = "phonestdcode", length = 5)
    private String phoneStdCode;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "pannumber", length = 10)
    private String panNumber;


    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;


    @Column(name = "hintquestion", length = 200)
    private String hintQuestion;

    @Column(name = "hintanswer", length = 200)
    private String hintAnswer;

    @Column(name = "corporateclass")
    private Long corporateClass;

    @Column(name = "prpoption")
    private Integer prpOption;

    @Column(name = "pupoption")
    private Integer pupOption;

    @Column(name = "biddercategory", length = 150)
    private String bidderCategory;

    @Column(name = "regnumber", length = 50)
    private String regNumber;

    @Column(name = "biddertype")
    private Integer bidderType;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "stateid")
    private Long stateId;

    @Column(name = "postalcode", length = 10)
    private String postalCode;

    @Column(name = "countryid")
    private Long countryId;

    @Column(name = "selfupdateddate")
    private LocalDateTime selfUpdatedDate;

    @Column(name = "isprivilegedbidder", nullable = false)
    private Boolean isPrivilegedBidder;

    @Column(name = "privilegemasterid")
    private Long privilegeMasterId;

    @Column(name = "mobileisdcode", length = 5)
    private String mobileIsdCode;

    @Column(name = "bidderuniqueid", length = 25, unique = true)
    private String bidderUniqueId;

    // Foreign key constraints
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private GepUser user;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "phoneisdcodeid", insertable = false, updatable = false)
//    private GepCountryMaster phoneIsdCode;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "privilegemasterid", insertable = false, updatable = false)
//    private GepBidderPrivilegeMaster privilegeMaster;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "stateid", insertable = false, updatable = false)
//    private GepMasterState state;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "countryid", insertable = false, updatable = false)
//    private GepCountryMaster country;
}