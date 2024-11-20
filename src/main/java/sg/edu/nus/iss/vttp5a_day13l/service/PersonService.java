package sg.edu.nus.iss.vttp5a_day13l.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.vttp5a_day13l.model.Person;
import sg.edu.nus.iss.vttp5a_day13l.repo.PersonRepo;

@Service
public class PersonService // 1:1, actually redudant since only 1 repo, in actual, service talks to many repo
{
    @Autowired
    PersonRepo personRepo;

    public List<Person> findAll()
    {
        return personRepo.findAll();
    }

    public Boolean create(Person person)
    {
        return personRepo.create(person);
    }

    public Boolean delete (Person person)
    {
        return personRepo.delete(person);
    }

    public Boolean update(Person person)
    {
        return personRepo.update(person);
    }
}
