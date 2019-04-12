package app.recipes.controllers;

import app.recipes.commands.RecipeCommand;
import app.recipes.services.ImageService;
import app.recipes.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController
{
    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService)
    {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/image")
    public String serveForm(@PathVariable Long id, Model model)
    {
        model.addAttribute("recipe", recipeService.findCommandById(id));

        return "recipe/imageUploadForm";
    }

    @GetMapping("/recipe/{id}/recipeimage")
    public void serveImage(@PathVariable Long id, HttpServletResponse response) throws IOException
    {
        RecipeCommand recipeCommand = recipeService.findCommandById(id);

        Byte[] image = recipeCommand.getImage();

        byte[] byteArray = new byte[image != null ? image.length : 0];

        if (image != null)
        {
            int i = 0;

            for (Byte wrappedByte : recipeCommand.getImage())
            {
                byteArray[i++] = wrappedByte; // auto unboxing.
            }
        }

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());
    }

    @PostMapping("/recipe/{id}/image")
    public String handleImagePost(@PathVariable Long id, @RequestParam("imagefile") MultipartFile file)
    {
        imageService.saveImage(id, file);

        return "redirect:/recipe/show/" + id;
    }
}
