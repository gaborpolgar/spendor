package org.training360.spendor.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from transactions")
public class TransactionControllerIT {

    TransactionDto transaction;

    @Autowired
    TestRestTemplate template;

    @BeforeEach
    void init() {
        transaction = template.postForObject("/api/spendor/transactions", new CreateTransCommand("szolgáltatás", 5000L,
                LocalDateTime.of(2021, 8, 01, 8, 03), "Thai masszázs"), TransactionDto.class);

        template.postForObject("/api/spendor/transactions", new CreateTransCommand("élelmiszer", 15000L,
                LocalDateTime.of(2021, 8, 03, 8, 03), "lidl"), TransactionDto.class);
    }

    @Test
    void testDeleteTransaction() {

        Long id = transaction.getId();

        template.delete("/api/spendor/transactions/" + id);

        List<TransactionDto> result = template.exchange("/api/spendor/transactions",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TransactionDto>>() {
                })
                .getBody();

        assertEquals(1, result.size());

        assertThat(result)
                .extracting(TransactionDto::getName)
                .containsExactly("élelmiszer");
    }

    @Test
    void testCreateTransaction() {

        TransactionDto transaction = template.postForObject("/api/spendor/transactions", new CreateTransCommand("elektronikai cikk", 200000L,
                LocalDateTime.of(2021, 7, 03, 8, 03), "edigital"), TransactionDto.class);


        TransactionDto expected = template.exchange("/api/spendor/transactions/" + transaction.getId(),
                HttpMethod.GET,
                null,
                TransactionDto.class)
                .getBody();

        assertEquals("elektronikai cikk", expected.getName());
        assertEquals(200000L, transaction.getAmount());
        assertEquals(LocalDateTime.of(2021, 7, 03, 8, 03), expected.getDate());
        assertEquals("edigital", expected.getLocation());
    }

    @Test
    void testListTransactions() {

//        assertEquals("élelmiszer", transactionDto.getName());
//        assertEquals(15000L, transactionDto.getAmount());
//        assertEquals(LocalDateTime.of(2021, 8, 03, 8, 03), transactionDto.getDate());
//        assertEquals("lidl", transactionDto.getLocation());

        List<TransactionDto> result = template.exchange("/api/spendor/transactions",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TransactionDto>>() {
                })
                .getBody();

        assertEquals(2, result.size());

        assertThat(result)
                .extracting(TransactionDto::getName)
                .containsExactly("szolgáltatás", "élelmiszer");
    }

    @Test
    void testGetAndUpdateTransactionById() {

        long id = transaction.getId();

        template.put("/api/spendor/transactions/" + id, new UpdateTransCommand("változás"));

        TransactionDto expected = template.exchange("/api/spendor/transactions/" + id,
                HttpMethod.GET,
                null,
                TransactionDto.class)
                .getBody();

        assertEquals("változás", expected.getName());
    }

//    @Test
//    void testCreateTransactionWithNullName() {
//
//        Problem expected = template.postForObject("/api/spendor/transactions", new CreateTransCommand(null, 200000L,
//                LocalDateTime.of(2021, 7, 03, 8, 03), "edigital"), Problem.class);
//
//        assertEquals(Status.BAD_REQUEST, expected.getStatus());
//    }

    @Test
    void testGetTransactionByNotExistId() {
        long id = transaction.getId();

        template.delete("/api/spendor/transactions/" + id);

        Problem expected = template.exchange("/api/spendor/transactions/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(new UpdateTransCommand("bírság")
                ),
                Problem.class)
                .getBody();

        assertEquals(Status.NOT_FOUND, expected.getStatus());
        assertEquals("Transaction with id " + id + " not found.", expected.getDetail());
        assertEquals("Not found", expected.getTitle());
        assertEquals(URI.create("api/spendor/transactions/not-found"), expected.getType());
    }

}

