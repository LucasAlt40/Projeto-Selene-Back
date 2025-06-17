package br.selene.projectseleneback.infra.services;

public interface IThirdPartyCheckout {
    void sendRequest(Object bodyRequestDto);
    void getCheckoutDetailsById(Integer id);
}
