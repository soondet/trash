package com.grpc.demo.impl;

import com.example.grpc.GreetServiceGrpc;
import com.example.grpc.GreetServiceProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GreetServiceImplTest {

    private ManagedChannel channel;
    private GreetServiceGrpc.GreetServiceBlockingStub blockingStub;

    @BeforeEach
    void setUp() {
        // Connect to the gRPC server (update localhost:9090 to match your server settings)
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext() // Disable SSL for local testing
                .build();
        blockingStub = GreetServiceGrpc.newBlockingStub(channel);
    }

    @AfterEach
    void tearDown() {
        // Shutdown the channel after each test
        channel.shutdown();
    }

    @Test
    void testGreet() {
        // Arrange
        GreetServiceProto.GreetRequest request = GreetServiceProto.GreetRequest.newBuilder()
                .setName("TestUser")
                .build();

        // Act
        GreetServiceProto.GreetResponse response = blockingStub.greet(request);

        // Assert
        assertEquals("Hello, TestUser!", response.getGreeting());
    }
}
