package com.example.test_backend.respository;

import com.example.test_backend.entity.Personalization;
import com.example.test_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PersonalizationRepository extends JpaRepository<Personalization, BigInteger> {
    Personalization findByPersonalizationUserId(User personalizationUserId);
}
