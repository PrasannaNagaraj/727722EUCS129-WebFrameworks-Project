package com.fundraising.donation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fundraising.donation.model.Donation;
@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer>{
    Donation findByEmail(String email);
    void deleteByEmail(String email);
}