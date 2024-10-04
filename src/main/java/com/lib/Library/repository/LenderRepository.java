package com.lib.Library.repository;

import com.lib.Library.entity.Lender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LenderRepository extends JpaRepository<Lender, String> {
}
