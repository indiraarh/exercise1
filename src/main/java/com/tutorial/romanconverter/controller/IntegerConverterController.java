package com.tutorial.romanconverter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tutorial.romanconverter.dto.IntegerRequestDTO;
import com.tutorial.romanconverter.model.IntegerConverter;

@Controller
public class IntegerConverterController {

    private String getIntegerConverterPage(int num, Model model) {
        final IntegerConverter integerConverter = new IntegerConverter(num);
        model.addAttribute("integerConverter", integerConverter);
        return "view-conversion-result-integer.html";
    }

    @GetMapping(value = "/convert-integer")
    public String getIntegerConverterWithView(Model model) {
        var integerRequestDTO = new IntegerRequestDTO();
        model.addAttribute("integerRequestDTO", integerRequestDTO);
        return "form-integer-converter.html";
    }

    @PostMapping(value = "/convert-integer")
    public String postIntegerConverterWithView(@ModelAttribute IntegerRequestDTO intRequestDTO, Model model) {
        int intFromView = intRequestDTO.getNum();
        return getIntegerConverterPage(intFromView, model);
    }
}
