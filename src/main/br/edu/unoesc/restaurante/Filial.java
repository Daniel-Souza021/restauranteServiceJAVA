package br.edu.unoesc.restaurante;

import com.sun.jdi.FloatValue;

import java.math.BigDecimal;
import java.util.List;

public class Filial {

    private String codigo;

    private String nome;

    private List<ItemCardapio> cardapio;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCardapio(List<ItemCardapio> cardapio) {
        this.cardapio = cardapio;
    }

    public List<ItemCardapio> getCardapio() {
        return cardapio;
    }

    public float getPrecoProdutoCardapio(Produto produto){
        for (ItemCardapio itemCardapio : cardapio) {
            if (itemCardapio.getProduto().equals(produto)) {
                return itemCardapio.getPreco().floatValue();
            }
        }
        return 0;
    }
}
