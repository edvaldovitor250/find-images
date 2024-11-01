package com.example.selenium_jsoup_example.service;

import com.example.selenium_jsoup_example.model.ImageUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

@Service
public class ImageScraperService {

    public List<ImageUrl> scrapeImages(String url) throws IOException {

        Set<String> imageUrls = new HashSet<>();
        List<ImageUrl> images = new ArrayList<>();

        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .get();

        // Itera sobre cada elemento <img> encontrado no documento HTML
        for (Element img : document.select("img")) {
            String src = img.absUrl("src");
            String title = img.attr("alt");

            if (!src.isEmpty()) {
                imageUrls.add(src);
                images.add(new ImageUrl(src, title));
            }

            // Obtém o URL absoluto do atributo "data-src" (caso seja usado para lazy-loading)
            String dataSrc = img.absUrl("data-src");
            if (!dataSrc.isEmpty()) {
                imageUrls.add(dataSrc);
                images.add(new ImageUrl(dataSrc, title));
            }

            // Obtém o valor do atributo "srcset" (contém múltiplas URLs de imagem)
            String srcSet = img.attr("srcset");

            // Itera sobre cada URL no "srcset" e adiciona à lista se for única
            for (String srcSetUrl : srcSet.split(",")) {
                String trimmedUrl = srcSetUrl.split(" ")[0].trim();
                if (!imageUrls.contains(trimmedUrl)) {
                    imageUrls.add(trimmedUrl);
                    images.add(new ImageUrl(trimmedUrl, title));
                }
            }
        }

        for (Element element : document.getAllElements()) {
            String style = element.attr("style");
            if (style.contains("background-image")) {
                String imageUrl = style.substring(style.indexOf("url(") + 4, style.indexOf(")")).replace("\"", "").replace("'", "");
                if (!imageUrls.contains(imageUrl)) {
                    images.add(new ImageUrl(imageUrl, "Imagem de fundo"));
                }
            }
        }

        return images;
    }
}
