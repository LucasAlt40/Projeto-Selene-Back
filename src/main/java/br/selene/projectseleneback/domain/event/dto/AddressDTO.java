package br.selene.projectseleneback.domain.event.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AddressDTO {

    @Size(max = 150, message = "A rua deve ter no máximo 150 caracteres.")
    private String street;

    @Size(max = 20, message = "O número deve ter no máximo 20 caracteres.")
    private String number;

    private String zipCode;

    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres.")
    private String neighbourhood;

    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres.")
    private String city;

    @Size(min = 2, max = 2, message = "O estado deve ser uma sigla de 2 letras (ex: SP).")
    @Pattern(regexp = "^[A-Z]{2}$", message = "O estado deve conter apenas letras maiúsculas (ex: SP).")
    private String state;

    public AddressDTO() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
