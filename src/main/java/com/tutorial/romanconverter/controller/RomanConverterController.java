package com.tutorial.romanconverter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tutorial.romanconverter.dto.RequestDTO;
import com.tutorial.romanconverter.model.RomanConverter;


@Controller
public class RomanConverterController {
    
    @GetMapping(value = "/")
    public String home(Model model) {
        return "view-home.html";
    }

    private String getRomanConverterPage(String roman, Model model) {
        try {
            final RomanConverter romanConverter = new RomanConverter(roman);
            model.addAttribute("romanConverter", romanConverter);
            return "view-conversion-result.html";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Terdapat kesalahan pada input.");
            return "invalid-page.html";
        }
    }
    
    private boolean isValidRoman(String roman) {
        String romanPattern = "^(?i)(M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3}))$";
        return roman.toUpperCase().equals(roman) && roman.matches(romanPattern);
    }

    @GetMapping(value = "/roman-converter/{roman}")
    public String romanConverterWithPathVariable(@PathVariable(value = "roman") String roman, Model model) {
        if (!isValidRoman(roman)) {
            model.addAttribute("errorMessage", "Terdapat kesalahan pada input.");
            return "invalid-page.html";
        }
        return getRomanConverterPage(roman, model);
    }

    @GetMapping(value = "/roman-converter")
    public String romanConverterWithReqParam(@RequestParam(value = "roman") String roman, Model model) {
        if (!isValidRoman(roman)) {
            model.addAttribute("errorMessage", "Terdapat kesalahan pada input.");
            return "invalid-page.html";
        }
        return getRomanConverterPage(roman, model);
    }

    @GetMapping(value = "/convert")
    public String getRomanConverterWithView(Model model) {
        var requestDTO = new RequestDTO();
        model.addAttribute("requestDTO", requestDTO);
        return "form.html";
    }

    @PostMapping(value = "/convert")
    public String postRomanConverterWithView(@ModelAttribute RequestDTO requestDTO, Model model) {
        String romanFromView = requestDTO.getRoman();
        return getRomanConverterPage(romanFromView, model);
    }

    @GetMapping(value = "/about-me")
    public String aboutMe(Model model) {
        model.addAttribute("nama", "Indira Arifia Rahamah");
        model.addAttribute("npm", "2206811846");
        return "about-me.html";
    }
    
}
