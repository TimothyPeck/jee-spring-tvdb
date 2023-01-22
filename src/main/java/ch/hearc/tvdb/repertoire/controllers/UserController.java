package ch.hearc.tvdb.repertoire.controllers;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.tvdb.repertoire.model.TvdbUser;
import ch.hearc.tvdb.repertoire.repository.TvdbUserRepository;

@Controller
public class UserController {
    @Autowired
    private TvdbUserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        model.addAttribute("logged", false);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/login-user")
    public String loginUser(@RequestParam String username, @RequestParam String email,
            @RequestParam String user_password, HttpSession session) {
        TvdbUser foundUser = userRepository.findByUsername(username);
        if (foundUser != null && foundUser.getPassword().equals(user_password)) {
            session.setAttribute("user", foundUser);
            return "redirect:/home";
        }
        return "redirect:/login";
    }

    @GetMapping("/create-account")
    public String register(Model model, HttpSession session) {
        model.addAttribute("logged", false);
        return "register";
    }

    @PostMapping("/create-user")
    public String createUser(@RequestParam String username, @RequestParam String email,
            @RequestParam String password, @RequestParam String password_verif, HttpSession session) {
        if (password.equals(password_verif) && userRepository.findByUsername(username) == null) {
            TvdbUser newUser = new TvdbUser();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(password);
            userRepository.save(newUser);
            session.setAttribute("user", newUser);
            return "redirect:/home";
        }
        return "redirect:/create-account";
    }
}
