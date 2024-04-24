package com.fundraising.donation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fundraising.donation.model.Fundraiser;
import com.fundraising.donation.repository.FundraiserRepository;

@Service
public class FundraiserService {
    
    @Autowired
    private FundraiserRepository fundraiserRepository;

    @Transactional
    public String saveFundraiser(Fundraiser fundraiser) {
        fundraiserRepository.save(fundraiser);
        return "Fundraising Request Successful";
    }
    @Transactional(readOnly = true)
    public Fundraiser getFundraiserDetails(String name) {
        return fundraiserRepository.findByName(name);
    }
    @Transactional(readOnly = true)
    public List<Fundraiser> getAllFundraiserDetails() {
        return fundraiserRepository.findAll();
    }
    @Transactional
    public void deleteFundraiser(String name){
        fundraiserRepository.deleteByName(name);
    }
    @Transactional
    public List<Fundraiser> pageNationSort(int pg,int sz,String name){
        Page<Fundraiser> cont= fundraiserRepository.findAll(PageRequest.of(pg,sz,Sort.by(Direction.DESC,name)));
        return cont.getContent();
    }
    @Transactional(readOnly = true)
    public List<Fundraiser> getbyquery(String name){
        return fundraiserRepository.findByEmail(name);
    }
}