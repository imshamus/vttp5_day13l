package sg.edu.nus.iss.vttp5a_day13l.repo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp5a_day13l.model.Person;

@Repository
public class PersonRepo {
    
    // private List<Person> persons; // obj not instantiated yet
    private List<Person> persons = new ArrayList<>();

    public PersonRepo() throws ParseException // this is a simulated that we created
    {
        String birthDate = "1988-12-01"; // date treated as string first, a bit mafan
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse(birthDate);
        
        persons.add(new Person("James", "Dyson", 99999, "James@Dyson.com", birthday, "92394905", 672839));

        // When displayed, format would not be what we want, like this "Thu Dec 01 00:00:00 GMT+08:00 1988", use thymeleaf date format (google search)
        // thymeleaf documentation, will come out one, format in html page
        // no. 19 appendix b, https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#dates, see edit in list.html

    }

    // Create reading from a csv file

    // Create writing to a csv file
    
    public List<Person> findAll()
    {
        return persons;
    }

    public Person findById(String personId) // returns me person that i found
    {
        Person foundPerson = persons.stream().filter(p -> p.getId().equals(personId)).findFirst().get();
        return foundPerson;
    }

    public Boolean create(Person person)
    {
        persons.add(person);
        return true;
    }

    public Boolean delete(Person person)
    {
        int iFoundPerson = persons.indexOf(person);
        
        if(iFoundPerson >= 0)
        {
            persons.remove(person);
            return true;
        }
        return false;
    }

    public Boolean update(Person person)
    {
        List<Person> filteredPerson = persons.stream().filter(p -> p.getId().equals(person.getId())).collect(Collectors.toList());
        // play cheat, remove original obj, add updated obj

        if(filteredPerson.size()>0)
        {
            persons.remove(filteredPerson.getFirst());
            persons.add(person);

            return true;
        }

        return false;
    }

        
}
