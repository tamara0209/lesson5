package kz.one.lab.project.Controller;

import io.swagger.annotations.ApiOperation;
import kz.one.lab.project.Entity.Person;
import kz.one.lab.project.Entity.Phone;
import kz.one.lab.project.PersonRepository.PersonRepository;
import kz.one.lab.project.PersonRepository.PhoneRepository;
import kz.one.lab.project.RandomGenerator.RandomGeneratorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("onelab")
@Slf4j
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RandomGeneratorImpl randomGenerator;

    @Autowired
    PhoneRepository phoneRepository;

    @ApiOperation("Save person")
    @RequestMapping(value = "/persons/",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public Person save(@RequestParam String name,@RequestParam int age){
        Person person = new Person(name,age);
        personRepository.save(person);
        log.info("New person was added " + person);
        return person;
    }

    @ApiOperation("Get person by name")
    @GetMapping(value = "/getPersonByName/",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getPersonByName(@RequestParam String name){
        List<Person> persons = personRepository.findAllByName(name);
        return persons;
    }

    @ApiOperation("Get person by age")
    @GetMapping(value = "/getPersonByAgeBetween/",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getPersonByAge(@RequestParam int start ,@RequestParam int end){
        List<Person> persons = personRepository.findAllByAgeBetween(start,end);
        return persons;
    }

    @ApiOperation("Generate person and phone's number")
    @PutMapping(value = "/generateUsers/" , produces = MediaType.APPLICATION_JSON_VALUE)
    public void generateUsers(){
        long start = System.currentTimeMillis();
        List<Person> persons = randomGenerator.generateUser(100);
        List<Integer> numbers = randomGenerator.generateNumber(100);
        for(int i=0;i<100;i++){
            personRepository.save(persons.get(i));
            phoneRepository.save(new Phone(numbers.get(i)));
        }
        long end = System.currentTimeMillis();
        log.info("Time wasted " + (end-start));
    }

    /*@ApiOperation("Time")
    @GetMapping(value = "/getTime/" , produces = MediaType.APPLICATION_JSON_VALUE)
    public long getTime(){
        long start = System.currentTimeMillis();
        List<Person> persons = personRepository.findAll();
        List<Phone> phone = phoneRepository.findAll();
        long end = System.currentTimeMillis();
        return (end-start);
    }*/
}
