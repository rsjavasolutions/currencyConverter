package com.rsjavasolution.currencyConverter.repository;

import com.rsjavasolution.currencyConverter.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.tools.JavaCompiler;

public interface ApiRepository extends JpaRepository<Log, Long> {
}
