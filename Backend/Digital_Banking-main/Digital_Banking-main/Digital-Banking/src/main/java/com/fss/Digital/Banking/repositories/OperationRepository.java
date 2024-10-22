package com.fss.Digital.Banking.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fss.Digital.Banking.entites.Operation;


public interface OperationRepository extends JpaRepository<Operation, Long> {
	//  Page<Operation> findByBankAccountIdOrderByOperationDateDesc(String compteId, Pageable pageable);

}
