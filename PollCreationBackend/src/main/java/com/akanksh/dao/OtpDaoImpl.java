package com.akanksh.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akanksh.entity.Otp;
import com.akanksh.enums.OtpPurpose;
import com.akanksh.repository.OtpRepository;

@Repository
public class OtpDaoImpl implements OtpDao{

	 @Autowired
	    private OtpRepository otpRepository;

	    @Override
	    public Otp saveOtp(Otp otp) {
	        return otpRepository.save(otp);
	    }

	    @Override
	    public Optional<Otp> findByEmailAndPurpose(String email, OtpPurpose purpose) {
	        return otpRepository.findByEmailAndPurpose(email, purpose);
	    }

	    @Override
	    public void deleteOtp(Otp otp) {
	        otpRepository.delete(otp);
	    }
}
