package com.example.carparkingapi.config.data.loader;

import com.example.carparkingapi.domain.Customer;
import com.example.carparkingapi.model.Role;
import com.example.carparkingapi.repository.CarRepository;
import com.example.carparkingapi.repository.CustomerRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    private final  PasswordEncoder passwordEncoder;

    private final CarRepository carRepository;

    private final Faker faker = new Faker();

    private static final Logger logger = LogManager.getLogger(DataLoader.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("loading test customers");
//        loadCustomers();
        logger.info("test customers loaded");
    }

    private void loadCustomers() {
        for (int i = 0; i < 20; i++) {
            Customer customer = new Customer();
            customer.setFirstName(faker.name().firstName());
            customer.setLastName(faker.name().lastName());
            customer.setUsername(faker.internet().emailAddress());
            customer.setPassword(passwordEncoder.encode("password"));
            customer.setRole(Role.USER);
            customer.setAccountEnabled(true);
            customer.setAccountNonExpired(true);
            customer.setAccountNonLocked(true);
            customer.setCredentialsNonExpired(true);

            customerRepository.save(customer);
        }
    }
}

