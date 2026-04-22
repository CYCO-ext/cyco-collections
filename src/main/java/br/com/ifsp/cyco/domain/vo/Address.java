package br.com.ifsp.cyco.domain.vo;

import br.com.ifsp.cyco.domain.exceptions.InvalidAddressException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Address {

    private final String street;
    private final String number;
    private final String city;
    private final String state;
    private final String zipCode;
    private final double latitude;
    private final double longitude;

    public Address(String street, String number, String city, String state,
                   String zipCode, double latitude, double longitude) {

        if (latitude < -90 || latitude > 90)
            throw new InvalidAddressException("Latitude inválida");

        if (longitude < -180 || longitude > 180)
            throw new InvalidAddressException("Longitude inválida");

        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Double.compare(address.latitude, latitude) == 0 &&
                Double.compare(address.longitude, longitude) == 0 &&
                Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, latitude, longitude);
    }
}
