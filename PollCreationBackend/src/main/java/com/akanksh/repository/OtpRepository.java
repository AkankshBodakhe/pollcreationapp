package com.akanksh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akanksh.entity.Otp;
import com.akanksh.enums.OtpPurpose;

public interface OtpRepository extends JpaRepository<Otp, Long>{
	 Optional<Otp> findByEmailAndPurpose(String email, OtpPurpose purpose);

}
