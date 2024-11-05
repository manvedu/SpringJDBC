package org.example.config;

import org.example.facade.BookingFacade;
import org.example.facade.BookingFacadeImpl;
import org.example.service.EventService;
import org.example.service.TicketService;
import org.example.service.UserService;
import org.example.model.TicketBatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public BookingFacade bookingFacade(UserService userService, EventService eventService, TicketService ticketService,
                                       Jaxb2Marshaller marshaller, PlatformTransactionManager transactionManager) {
        return new BookingFacadeImpl(userService, eventService, ticketService, marshaller, transactionManager);
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(TicketBatch.class);
        return marshaller;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb"); // Example H2 in-memory database URL
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
