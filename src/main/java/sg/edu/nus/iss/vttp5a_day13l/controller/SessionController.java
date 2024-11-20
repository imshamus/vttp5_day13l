package sg.edu.nus.iss.vttp5a_day13l.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/session")
public class SessionController 
{
    @GetMapping("/firstpage")
    public String firstPage()
    {
        return "pageA";
    }

    @PostMapping("/firstpage")
    public String postFromFirstpage(@RequestBody MultiValueMap<String, String> entity, HttpSession session, Model model) {
        String fName = entity.getFirst("fullname");
        session.setAttribute("myname", fName);

        model.addAttribute("myFullName", fName);
        return "pageB";
    }

    @GetMapping("/thirdpage")
    public String thirdPage(HttpSession session, Model model) {
        if (session.getAttribute("myname") == null) // if no value keep redirect to firstPage
            return "redirect:/session/firstpage";

        model.addAttribute("myFullName", session.getAttribute("myname"));
        return "pageC";
    }

    @GetMapping("/reset")
    public String reset(HttpSession session, Model model)
    {
        session.invalidate(); // clears the session

        return "redirect:/session/firstpage";

        // can also return "return "pageA"; won't go through the controller w this, directly loads pageA
    }

    // Session is a memory location
    // Session obj helps you log who login etc, holds up memory on the server, nowadays no more like that, too much will crash sys?
}
