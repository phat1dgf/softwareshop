package com.webcuoiky.softwareshop.controller;


import com.webcuoiky.softwareshop.model.User;
import com.webcuoiky.softwareshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {

    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String phone,
                               @RequestParam String password,
                               RedirectAttributes redirectAttributes) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone_number(phone);
        user.setPassword(password);
        user.setRole("ROLE_USER");

        try {
            boolean isRegistered = userService.register(user);
            if (isRegistered) {
                redirectAttributes.addFlashAttribute("message", "Đăng ký thành công!");
                return "redirect:/login";
            } else {
                redirectAttributes.addFlashAttribute("error", "Email đã được sử dụng!");
                return "redirect:/register";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {




        return "login"; // Trả về login.html
    }
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            RedirectAttributes redirectAttributes
                            ) {
        try {

            //User user = userService.login(email, password);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(email, password);

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/index";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }

    }
}
