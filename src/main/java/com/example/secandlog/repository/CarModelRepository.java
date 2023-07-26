package com.example.secandlog.repository;

import com.example.secandlog.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
}
