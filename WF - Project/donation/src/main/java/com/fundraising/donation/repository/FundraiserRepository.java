package com.fundraising.donation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fundraising.donation.model.Fundraiser;
import java.util.List;
@Repository
public interface FundraiserRepository extends JpaRepository<Fundraiser, Integer>{
    void deleteByName(String name);
    Fundraiser findByName(String name);
    @Query(value = "Select a from Fundraiser a where a.email=:b")
    List<Fundraiser> findByEmail(@Param("b") String email);
}
