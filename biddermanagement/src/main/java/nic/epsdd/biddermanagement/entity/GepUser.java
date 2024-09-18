package nic.epsdd.biddermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gep_user", schema = "public")
public class GepUser {
    @Id
    private Long id;

    @Column(name = "loginid", length = 50, unique = true)
    private String loginId;

    @Column(name = "password", length = 150)
    private String password;

    @Column(name = "usertype", length = 20)
    private String userType;

    @Column(name = "createddate", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "createdby")
    private Long createdBy;

    @Column(name = "verifydate")
    private LocalDateTime verifyDate;

    @Column(name = "verifiedby")
    private Long verifiedBy;

    @Column(name = "userstatus", length = 20, nullable = false)
    private String userStatus;

    @Column(name = "rejectreason", length = 200)
    private String rejectReason;

    @Column(name = "dscauth")
    private Integer dscAuth;

    @Column(name = "lastlogin")
    private LocalDateTime lastLogin;

    @Column(name = "pwdchanged")
    private Boolean pwdChanged = false;

    @Column(name = "pwdhash", length = 150, nullable = false)
    private String pwdHash = "d";

    @Column(name = "updateddate")
    private LocalDateTime updatedDate;

    @Column(name = "updatedby")
    private Long updatedBy;

    @Column(name = "displayname", length = 500, nullable = false)
    private String displayName;

    @Column(name = "mobileno", length = 20, nullable = false)
    private String mobileNo;

    @Column(name = "alternateemailid", length = 255, nullable = false)
    private String alternateEmailId;

    @Column(name = "mobileisdcodeid")
    private Long mobileIsdCodeId;

    @Column(name = "mobileisdcode", length = 5)
    private String mobileIsdCode;
}
