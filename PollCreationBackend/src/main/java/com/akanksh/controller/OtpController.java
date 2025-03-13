package com.akanksh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.akanksh.dto.OtpDto;
import com.akanksh.enums.OtpPurpose;
import com.akanksh.service.OtpService;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateOtp(@RequestParam String email, @RequestParam OtpPurpose purpose) {
        boolean otpGenerated = otpService.generateAndSendOtp(email, purpose);
        if (otpGenerated) {
            return ResponseEntity.ok("OTP sent successfully to " + email);
        } else {
            return ResponseEntity.badRequest().body("Failed to generate OTP");
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateOtp(@RequestBody OtpDto otpDto) {
        boolean isValid = otpService.validateOtp(otpDto);
        if (isValid) {
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired OTP");
        }
    }
}
