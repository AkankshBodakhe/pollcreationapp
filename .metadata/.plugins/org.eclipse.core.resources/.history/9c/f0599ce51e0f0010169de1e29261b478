package com.akanksh.dao;

import java.util.Optional;

import com.akanksh.entity.Otp;
import com.akanksh.enums.OtpPurpose;

public interface OtpDao {

    Otp saveOtp(Otp otp);

    Optional<Otp> findByEmailAndPurpose(String email, OtpPurpose purpose);

    void deleteOtp(Otp otp);
}
