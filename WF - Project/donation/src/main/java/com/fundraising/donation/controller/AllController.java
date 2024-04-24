package com.fundraising.donation.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fundraising.donation.model.Donation;
import com.fundraising.donation.model.Fundraiser;
import com.fundraising.donation.model.Signup;
import com.fundraising.donation.service.DonationService;
import com.fundraising.donation.service.FundraiserService;
import com.fundraising.donation.service.SignupService;

@RestController
public class AllController {
    @Autowired
    private SignupService signupService;
    
    @Autowired
    private DonationService donationService;

    @Autowired
    private FundraiserService fundraiserService;

    @PostMapping("/signup/post")
    public String saveUser(@RequestBody Signup signup) {
        return signupService.saveUser(signup);
    }
    @GetMapping("/signin")
    public String validate(@RequestParam String email,@RequestParam String password) {
        return signupService.validate(email, password);
    }
    @GetMapping("/signin/get")
    public Signup getSignupDetails(@RequestParam String email) {
        return signupService.getsignupDetails(email);
    }
    @GetMapping("/signin/getAll")
    public List<Signup> getSignupDetails() {
        return signupService.getAllSignUp();
    }
    @DeleteMapping("/signin/delete")
    public String deletelogin(@RequestParam String email) {
        Signup ans = signupService.getsignupDetails(email);
        if(ans != null){
            signupService.deletelogindetail(email);
            return "Fundraiser deleted";
        }
        else
        return "No data found";
    }
    @PutMapping("/signin/update")
    public String updatelogin(@RequestBody Signup newLogin){
        Signup oldlogin=signupService.getsignupDetails(newLogin.getEmail());
        if(oldlogin!=null){
            oldlogin.setEmail(newLogin.getEmail());
            oldlogin.setUsername(newLogin.getUsername());
            signupService.saveUser(oldlogin);
            return "Login Updated";
        }
        return "User Not Found";
    }
    @GetMapping("/signin/paging/{pg}/{sz}/{name}")
    public List<Signup> pagination(@PathVariable("pg") int pg,@PathVariable("sz") int sz,@PathVariable("name") String name) {
        return signupService.paginationSort(pg,sz,name);
    }
    
    @PostMapping("/donation/post")
    public String saveDonation(@RequestBody Donation donation) {
        return donationService.saveDonation(donation);
    }
    @GetMapping("/donation/get")
    public Donation getDonationDetails(@RequestParam String email) {
        return donationService.getDonationDetails(email);
    }
    @GetMapping("/donation/getAll")
    public List<Donation> getDonationDetails() {
        return donationService.getAllDonationDetails();
    }
    @PutMapping("/donation/update")
    public String updatedonation(@RequestBody Donation newDonation){
        Donation oldDonation=donationService.getDonationDetails(newDonation.getEmail());
        if(oldDonation!=null){
            oldDonation.setAmount(newDonation.getAmount());
            oldDonation.setEmail(newDonation.getEmail());
            oldDonation.setUsername(newDonation.getUsername());
            donationService.saveDonation(oldDonation);
            return "Donation Updated";
        }
        return "Donation Not Found";
    }
    @DeleteMapping("/donation/delete")
    public String deleteDonation(@RequestParam String email){
        if(donationService.getDonationDetails(email)!=null)
        donationService.deleteDonation(email);
        else
        return "No data found";
        return "Donation deleted";
    }
    @GetMapping("/donation/paging/{pg}/{sz}/{name}")
    public List<Donation> Donationpagination(@PathVariable("pg") int pg,@PathVariable("sz") int sz,@PathVariable("name") String name) {
        return donationService.pageNationSort(pg,sz,name);
    }
    
    @PostMapping("/fundraiser/post")
    public String saveFundraiser(@RequestBody Fundraiser fundraiser) {
        return fundraiserService.saveFundraiser(fundraiser);
    }
    @GetMapping("/fundraiser/get")
    public Fundraiser getFundraiserDetails(@RequestParam String name) {
        return fundraiserService.getFundraiserDetails(name);
    }

    @GetMapping("/fundraiser/getAll")
    public List<Fundraiser> getFundraiserDetails() {
        return fundraiserService.getAllFundraiserDetails();
    }
    
    @DeleteMapping("/fundraiser/delete")
    public String deleteStudent(@RequestParam String name){
        if(fundraiserService.getFundraiserDetails(name)!=null)
        fundraiserService.deleteFundraiser(name);
        else
        return "No data found";
        return "Fundraiser deleted";
    }

    @PutMapping("/fundraiser/update")
    public ResponseEntity<String> updateStudent(@RequestBody Fundraiser newFundraiser){
        Fundraiser oldFundraiser=fundraiserService.getFundraiserDetails(newFundraiser.getName());
        if(oldFundraiser!=null){
            oldFundraiser.setEmail(newFundraiser.getEmail());
            oldFundraiser.setDescription(newFundraiser.getDescription());
            oldFundraiser.setGoalamount(newFundraiser.getGoalamount());
            oldFundraiser.setEnddate(newFundraiser.getEnddate());
            fundraiserService.saveFundraiser(oldFundraiser);
        }
        else
        return new ResponseEntity<>("not found",HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>("Updated",HttpStatus.OK);
    }
    @GetMapping("/fundraiser/paging/{pg}/{sz}/{name}")
    public List<Fundraiser> getMethodName(@PathVariable("pg") int pg,@PathVariable("sz") int sz,@PathVariable("name") String name) {
        return fundraiserService.pageNationSort(pg,sz,name);
    }
    @GetMapping("/fundraiser/query")
    public List<Fundraiser> getquery(@RequestParam String email){
        return fundraiserService.getbyquery(email);
    }
}
