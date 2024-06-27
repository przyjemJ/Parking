package com.example.carparkingapi.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    ////// URLs //////

    public static final String CUSTOMER_URL = "/api/v1/customer/**";

    public static final String ADMIN_URL = "/api/v1/admin/**";

    public static final String AUTH_URL = "/api/v1/auth/**";

    public static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "v2/api-docs",
            "/swagger-resources",
            "swagger-resources",
            "/swagger-resources/**",
            "swagger-resources/**",
            "/configuration/ui",
            "configuration/ui",
            "/configuration/security",
            "configuration/security",
            "/swagger-ui.html",
            "swagger-ui.html",
            "webjars/**",
            // -- Swagger UI v3
            "/v3/api-docs/**",
            "v3/api-docs/**",
            "/swagger-ui/**",
            "swagger-ui/**",
    };

    ////// Roles //////

    public static final String USER = "USER";

    public static final String ADMIN = "ADMIN";

    ////// Entity types //////

    public static final String CUSTOMER = "customer";

    public static final String CAR = "car";

    public static final String PARKING = "parking";

    ////// Customer field names //////

    public static final String FIRST_NAME = "firstName";

    public static final String LAST_NAME = "lastName";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    private static final String CUSTOMER_FIELDS = "username, password, firstName, lastName";

    ////// Car field names //////

    public static final String BRAND = "brand";

    public static final String MODEL = "model";

    public static final String PRICE = "price";

    public static final String LENGTH = "length";

    public static final String WIDTH = "width";

    public static final String DATE_OF_PRODUCTION = "dateOfProduction";

    public static final String FUEL = "fuel";

    private static final String CAR_FIELDS = "brand, model, price, length, width, dateOfProduction, fuel";

    ////// Parking field names //////

    public static final String PARKING_NAME = "name";

    public static final String ADRESS = "adress";

    public static final String CAPACITY = "capacity";

    public static final String PARKING_TYPE = "parkingType";

    public static final String PARKING_SPOT_WIDTH = "parkingSpotWidth";

    public static final String PARKING_SPOT_LENGTH = "parkingSpotLength";

    public static final String PLACES_FOR_ELECTRIC_CARS = "placesForElectricCars";

    private static final String PARKING_FIELDS = "name, address, capacity, parkingType, parkingSpotWidth, " +
            "parkingSpotLength, placesForElectricCars";

    ////// Error messages //////

    public static final String USER_NOT_FOUND_ERROR_MESSAGE = "User not found";

    public static final String PARKING_NOT_FOUND_ERROR_MESSAGE = "Parking not found";

    public static final String CAR_NOT_FOUND_ERROR_MESSAGE = "Parking not found";

    private static final String INVALID_FIELD_NAME_ERROR_MESSAGE = "Invalid field name, choose from ";

    public static final String CUSTOMER_FIELD_ERROR_MESSAGE = INVALID_FIELD_NAME_ERROR_MESSAGE + CUSTOMER_FIELDS;

    public static final String CAR_FIELD_ERROR_MESSAGE = INVALID_FIELD_NAME_ERROR_MESSAGE + CAR_FIELDS;

    public static final String PARKING_FIELD_ERROR_MESSAGE = INVALID_FIELD_NAME_ERROR_MESSAGE + PARKING_FIELDS;

    public static final String INVALID_ENTITY_TYPE_ERROR_MESSAGE = "Invalid entity type choose from customer, car, parking";

    public static final String ADMIN_NOT_AUTHORIZED_ERROR_MESSAGE = "Admin not authorized";

    public static final String CUSTOMER_NOT_AUTHORIZED_ERROR_MESSAGE = "Customer not authorized";

    public static final String PARKING_FULL_ERROR_MESSAGE = "Parking is already full";

    public static final String PARKING_SPACE_TO_SMALL_ERROR_MESSAGE = "Parking space is too small for this car";

    public static final String NO_MORE_ELECTRIC_PLACES_ERROR_MESSAGE = "This parking has no more electric places";

    public static final String PARKING_NOT_ALLOW_LPG_CAR_ERROR_MESSAGE = "This parking does not allow LPG cars";



}
