package com.example.secandlog.iservice;

import com.example.secandlog.dto.CarModelDTO;
import com.example.secandlog.model.CarModel;

import java.util.List;

public interface ICarModel {
    List<CarModel> getAllCarModels();

    CarModel saveCarModel(CarModelDTO carModelDTO);

    CarModel getCarModelById(Long id);

    CarModel updateCarModel(CarModelDTO carModelDTO, Long id);

    void deleteCarModelById(Long id);
}
