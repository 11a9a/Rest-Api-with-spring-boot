package com.example.secandlog.service;

import com.example.secandlog.dto.CarModelDTO;
import com.example.secandlog.iservice.ICarModel;
import com.example.secandlog.model.CarModel;
import com.example.secandlog.repository.CarModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarModelService implements ICarModel {

    private final CarModelRepository carModelRepository;

    @Override
    public List<CarModel> getAllCarModels() {
        return carModelRepository.findAll();
    }

    @Override
    public CarModel saveCarModel(CarModelDTO carModelDTO) {
        CarModel carModel = new CarModel(null, carModelDTO.getName(), carModelDTO.getEngine(), carModelDTO.getColor(), carModelDTO.getCreatedYear());
        return carModelRepository.save(carModel);
    }

    @Override
    public CarModel getCarModelById(Long id) {
        return carModelRepository.findById(id).get();
    }

    @Override
    public CarModel updateCarModel(CarModelDTO carModelDTO, Long id) {
        CarModel carModel = new CarModel(id, carModelDTO.getName(), carModelDTO.getEngine(), carModelDTO.getColor(), carModelDTO.getCreatedYear());
        return carModelRepository.save(carModel);
    }

    @Override
    public void deleteCarModelById(Long id) {
        carModelRepository.deleteById(id);
    }
}
