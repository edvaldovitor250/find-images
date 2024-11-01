package com.example.selenium_jsoup_example.controller;

import com.example.selenium_jsoup_example.model.ImageUrl;
import com.example.selenium_jsoup_example.service.ImageScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class ImageController {

    @Autowired
    private ImageScraperService imageScraperService;

    @GetMapping("/")
    public String showInputForm() {
        return "inputForm";
    }

    @GetMapping("/fetch-images")
    public String fetchImages(@RequestParam("url") String url, Model model) {
        try {
            List<ImageUrl> images = imageScraperService.scrapeImages(url);
            model.addAttribute("images", images);
            return "imageResults";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Erro ao buscar imagens.");
            return "error";
        }
    }

}
