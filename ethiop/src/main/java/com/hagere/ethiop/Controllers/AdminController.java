package com.hagere.ethiop.Controllers;



import com.hagere.ethiop.Entity.Admin;
import com.hagere.ethiop.repositories.AdminRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@Data
public class AdminController {
    @Autowired
    private final AdminRepository adminRepository;


    @GetMapping("/AdminRegister.html")
    public String registrationForm(Model model, Admin Admin) {
        model.addAttribute("Admin",Admin);

        return "AdminRegister";
    }



    @PostMapping("/registerAd")
    public String processRegister(@ModelAttribute Admin admin, HttpSession session) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);

       adminRepository.save(admin);

        return "redirect:/main.html";
    }
}
