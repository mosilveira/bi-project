package com.example.bi.repositories;

import com.example.bi.entities.DataInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<DataInfo, Long> {
}
