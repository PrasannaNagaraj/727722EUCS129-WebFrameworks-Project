package com.fundraising.donation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fundraising.donation.model.Donation;
import com.fundraising.donation.repository.DonationRepository;

import jakarta.transaction.Transactional;

@Service
public class DonationService {
    
    @Autowired
    private DonationRepository donationRepository;
    @Transactional
    public String saveDonation(Donation donation) {
        donationRepository.save(donation);
        return "Donation Successful";
    }
    @Transactional
    public Donation getDonationDetails(String email) {
        return donationRepository.findByEmail(email);
    }
    @Transactional
    public List<Donation> getAllDonationDetails() {
        return donationRepository.findAll();
    }
    @Transactional
    public void deleteDonation(String email){
        donationRepository.deleteByEmail(email);
    }
    @Transactional
    public List<Donation> pageNationSort(int pg,int sz,String name){
        Page<Donation> cont= donationRepository.findAll(PageRequest.of(pg,sz,Sort.by(Direction.DESC,name)));
        return cont.getContent();
    }
}
