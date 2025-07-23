package com.kaneverse.spring_boot_library.dao;

import com.kaneverse.spring_boot_library.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    Checkout findByUserEmailAndBookId(String userEmail, Long bookId);

    List<Checkout> findBooksByUserEmail(String userEmail);

    @Transactional
    @Modifying
    @Query("DELETE FROM Checkout c WHERE c.bookId = :bookId")
    void deleteAllByBookId(@Param("bookId") Long bookId);

}