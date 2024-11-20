package sg.edu.nus.iss.vttp5a_day13l.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Person 
{
    // Attributes & their annotations (constraints)
    // @NotNull(message = "id must be auto generated.")
    private String id;

    @NotEmpty(message = "First name is mandatory.")
    @Size(min = 2, max = 60, message = "First name must be between 2-60 characters.")
    private String firstName;

    @NotEmpty(message = "Last name is mandatory.")
    @Size(min = 2, max = 60, message = "Last name must be between 2-60 characters.")
    private String lastName;

    @Min(value = 1500, message = "Min salary starts from 1500.")
    @Max(value = 50000, message = "Max salary ceiling is 50000.")
    private Integer salary;

    @Email(message = "Email input does not conform to email format.")
    @NotBlank(message = "Email is mandatory") // similar to NotEmpty
    private String email;

    @Past(message = "Date must be in the past")
    @NotNull(message = "You must set your DOB")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    // Constructor
    public Person() // fields are initialised as null 
    {
        
    }

    public Person(String firstName, String lastName, Integer salary, String email, Date dob) {
        this.id = UUID.randomUUID().toString(); // We wanna generate a random ID but usually in actual case not like that
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.email = email;
        this.dob = dob;
    }

    // Getter Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }



    @Override
    public String toString() {
        return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary
                + ", email=" + email + ", dob=" + dob + "]";
    }

    

    

    


}
