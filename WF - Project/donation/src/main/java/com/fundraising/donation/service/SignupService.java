package com.fundraising.donation.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.fundraising.donation.model.Signup;
import com.fundraising.donation.repository.SignupRepository;

import jakarta.transaction.Transactional;

@Service
public class SignupService {
    
    @Autowired
    private SignupRepository signupRepository;

    @Transactional
    public String saveUser(Signup signup) {
        signupRepository.save(signup);
        return "User saved";
    }
    
    @Transactional
    public String validate(String email,String password){
        Signup signup = signupRepository.findByEmail(email);
        if(signup == null){
            return "User not found";
        }
        if(signup.getPassword().equals(password)){
            return "Login successful";
        }
        return "Invalid password";
    }
    public Signup getsignupDetails(String email){
        return signupRepository.findByEmail(email);
    }
    public List<Signup> getAllSignUp(){
        return signupRepository.findAll();
    }
    @Transactional
    public void deletelogindetail(String email){
        signupRepository.deleteByEmail(email);
    }
    @Transactional
    public List<Signup> paginationSort(int pg,int sz,String name){
        Page<Signup> cont= signupRepository.findAll(PageRequest.of(pg,sz,Sort.by(Direction.DESC, name)));    
        return cont.getContent();
    }
}
