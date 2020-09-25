package br.edu.unoesc.restaurante;

import java.util.ArrayList;
import java.util.List;

public class Comanda {

    private Integer codigo;

    private Filial filial;

    private List<ItemComanda> items = new ArrayList<ItemComanda>();

    private StatusComanda status;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public void adicionarItemComanda(ItemComanda itemcomanda) {
        items.add(itemcomanda);
    }

    public List<ItemComanda> getItems() {
        return items;
    }

    public StatusComanda getStatus() {
        return status;
    }

    public void setStatus(StatusComanda status) {
        this.status = status;
    }
}
