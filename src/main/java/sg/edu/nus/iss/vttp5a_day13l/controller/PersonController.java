package sg.edu.nus.iss.vttp5a_day13l.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5a_day13l.model.Person;
import sg.edu.nus.iss.vttp5a_day13l.service.PersonService;

@Controller
@RequestMapping("/persons") // always plural because richard maturity model (???) std use plural
public class PersonController 
{
    @Autowired
    PersonService personService; // controller only talk to service layer

    @GetMapping("")
    public String personListing(Model model)
    {
        List<Person> persons = personService.findAll(); // injecting in information
        model.addAttribute("persons", persons);

        return "list";
    }

    @GetMapping(path = "/create")
    public String createForm(Model model)
    {
        Person p = new Person();
        model.addAttribute("person", p);

        return "create";
    }

    @PostMapping("/create")
    public String postCreateForm(@Valid @ModelAttribute ("person") Person person, BindingResult result, Model model) 
    // valid is validation, model attirbute is the th:object that was passed in from create.html (name must match ${person}) 
    {
        if (result.hasErrors())
        {
            return "create"; // stays on the same page
        }

        Person p = new Person(person.getFirstName(), person.getLastName(), person.getSalary(), person.getEmail(), person.getDob(), person.getTelephone(), person.getPostalCode());
        personService.create(p);
        return "redirect:/persons"; // redirect, refresh, repopulate
    }

    @GetMapping("/delete/{person-id}")
    public String deletePerson(@PathVariable("person-id") String personId)
    {
        Person p = personService.findById(personId); // if person found thn delete?
        personService.delete(p);

        return "redirect:/persons";
    }

    @GetMapping(path = "/update/{person-id}")
    public String updateForm(@PathVariable("person-id") String personId, Model model) {
        Person p = personService.findById(personId);
        model.addAttribute("person", p);
        return "update";
    }

    @PostMapping("/update")
    public String postUpdateForm(@Valid @ModelAttribute("person") Person person, BindingResult result, Model model) {

        if (result.hasErrors())
            return "update";
        
        personService.update(person);

        return "redirect:/persons";
    }
}
