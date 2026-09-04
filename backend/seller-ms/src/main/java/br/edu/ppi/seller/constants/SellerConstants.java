package br.edu.ppi.seller.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerConstants {

    public static final String SELLER_MESSAGE_201 = "Vendedor cadastrado com sucesso!";
    public static final String SELLER_MESSAGE_204 = "Vendedor não localizado";
    public static final String SELLER_MESSAGE_400 = "Entrada de dados inválida";
    public static final String SELLER_MESSAGE_500 = "Erro interno";
}
