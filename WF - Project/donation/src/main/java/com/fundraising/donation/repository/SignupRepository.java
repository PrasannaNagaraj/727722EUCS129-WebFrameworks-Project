package com.fundraising.donation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundraising.donation.model.Signup;


@Repository
public interface SignupRepository extends JpaRepository<Signup, Integer>{
    Signup findByEmail(String email);
    void deleteByEmail(String email);
}
