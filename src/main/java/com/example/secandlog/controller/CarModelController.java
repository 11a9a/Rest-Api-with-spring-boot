package com.example.secandlog.controller;

import com.example.secandlog.dto.CarModelDTO;
import com.example.secandlog.iservice.ICarModel;
import com.example.secandlog.model.CarModel;
//import jakarta.validation.Valid;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class CarModelController {

    private final ICarModel iCarModel;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/carModels")
    public String getAllCarModels(Model model) {
        model.addAttribute("carModels", iCarModel.getAllCarModels());
        return "carModels";
    }

    @GetMapping("/carModels/new")
    public String createCarModel(Model model) {
        CarModel carModel = new CarModel();
        model.addAttribute("carModel", carModel);
        return "create_carModel";
    }

    @PostMapping("/carModels")
    public String addCarModel(@Valid @RequestBody @ModelAttribute("carModel") CarModelDTO carModelDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create_carModel";
        }
        iCarModel.saveCarModel(carModelDTO);
        return "redirect:/carModels";
    }

    @GetMapping("/carModels/edit/{id}")
    public String editCarModel(@PathVariable Long id, Model model) {
        model.addAttribute("carModel", iCarModel.getCarModelById(id));
        return "edit_carModel";
    }

    @PostMapping("/carModels/{id}")
    public String updateCarModel(@PathVariable Long id, @Valid @RequestBody @ModelAttribute("carModel") CarModelDTO carModelDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_carModel";
        }
        iCarModel.updateCarModel(carModelDTO, id);
        return "redirect:/carModels";
    }

    @GetMapping("/carModels/{id}")
    public String deleteCarModel(@PathVariable Long id) {
        iCarModel.deleteCarModelById(id);
        return "redirect:/carModels";
    }

}
