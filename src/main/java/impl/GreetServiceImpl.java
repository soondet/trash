package impl;

import com.example.grpc.GreetServiceGrpc;
import com.example.grpc.GreetServiceProto;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetServiceProto.GreetRequest request, StreamObserver<GreetServiceProto.GreetResponse> responseObserver) {
        String greeting = "Hello, " + request.getName() + "!";
        GreetServiceProto.GreetResponse response = GreetServiceProto.GreetResponse.newBuilder()
                .setGreeting(greeting)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
