package com.remmycoder.zenstatebackend.user;

import com.remmycoder.zenstatebackend.util.ResidentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resident")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID          id;
    @Column(name = "firstName", nullable = false)
    private String        firstName;
    @Column(name = "lastName", nullable = false)
    private String        lastName;
    @Column(nullable = false, unique = true)
    private String        email;
    @Column(nullable = false)
    private String        password;
    @Column(name = "dialCode", nullable = false)
    private String        dialCode;
    @Column(name = "phoneNumber", nullable = false)
    private String        phoneNumber;
    @Column(nullable = false)
    private String        gender;
    @Enumerated(EnumType.STRING)
    @Column(name = "residentType", nullable = false)
    private ResidentType  residentType;
    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(residentType.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
