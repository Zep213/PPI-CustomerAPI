package br.edu.ppi.seller.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConstants {

    public static final String PRODUCT_MESSAGE_201 = "Produto cadastrado com sucesso";
    public static final String PRODUCT_MESSAGE_204 = "Produto não encontrado";
    public static final String PRODUCT_MESSAGE_400 = "Entrada de dados inválida";
    public static final String PRODUCT_MESSAGE_500 = "Erro interno";
}
