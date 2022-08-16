package dev.dneversky.pioneer.gateway.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.SpecServiceGrpc;
import org.dneversky.gateway.SpecServiceOuterClass;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SpecService {

    @GrpcClient("cloud-grpc-server")
    private SpecServiceGrpc.SpecServiceBlockingStub serviceBlockingStub;

    public List<SpecServiceOuterClass.Spec> getSpecsByIds(Collection<String> specsIds) {
        SpecServiceOuterClass.Specs response = serviceBlockingStub
                .getSpecsByIds(SpecServiceOuterClass.SpecsIds.newBuilder()
                        .addAllIds(specsIds)
                        .build());
        return response.getSpecsList();
    }
}
