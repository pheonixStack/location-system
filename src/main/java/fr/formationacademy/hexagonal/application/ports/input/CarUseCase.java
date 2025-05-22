package fr.formationacademy.hexagonal.application.ports.input;

import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.CarRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.ReturnCarRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.UpdateCarStatusRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.CarProfitabilityResponse;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.ReturnCarResponse;

public interface CarUseCase {

    void createCar(CarRequest request);

    void updateStatus(UpdateCarStatusRequest request);

    ReturnCarResponse returnCar(ReturnCarRequest returnCarRequest);

    CarProfitabilityResponse calculate(Long carId);

}

