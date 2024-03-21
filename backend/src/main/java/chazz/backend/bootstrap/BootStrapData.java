package chazz.backend.bootstrap;

import chazz.backend.dao.CustomerRepository;
import chazz.backend.dao.DivisionRepository;
import chazz.backend.entities.Customer;
import chazz.backend.entities.Division;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Division division = divisionRepository.findById(33L)
                .orElseThrow(() -> new RuntimeException("Division not found"));
        Customer george = new Customer("George", "Washington", "123 Apple St.", "12345","123-456-7890", division);
        Customer jane = new Customer("Jane", "Doe", "124 Apple St.", "12345", "123-456-7891", division);
        Customer jim = new Customer("Jim", "Beam", "125 Apple St.", "12345", "123-456-7892", division);
        Customer jack = new Customer("Jack", "Daniels", "126 Apple St.", "12345", "123-456-7893", division);
        Customer jill = new Customer("Jill", "Valentine", "127 Apple St.", "12345", "123-456-7894", division);

        // Save customers to the repository
        customerRepository.save(george);
        customerRepository.save(jane);
        customerRepository.save(jim);
        customerRepository.save(jack);
        customerRepository.save(jill);

        division.getCustomers().add(george);
        division.getCustomers().add(jane);
        division.getCustomers().add(jim);
        division.getCustomers().add(jack);
        division.getCustomers().add(jill);

        Iterable<Customer> customers = customerRepository.findAll();
        customers.forEach(customer -> System.out.println(customer.getFirstName() + " " + customer.getLastName()));
    }
}
