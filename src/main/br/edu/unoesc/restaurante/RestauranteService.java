package br.edu.unoesc.restaurante;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RestauranteService {

    private List<Comanda> comandas = new ArrayList<Comanda>();
    private List<Filial> filiais;
    private Integer codigoComanda = 0;

    public RestauranteService(List<Filial> filiais) {
        this.filiais = filiais;
    }

    public Comanda abrirComanda(String codigoFilial) throws DadosInvalidosException {
        codigoComanda++;

        Filial objetoFilial = buscarFilial(codigoFilial);
        if(objetoFilial == null){
            throw new DadosInvalidosException("Filial não encontrada.");
        }
        Comanda comanda = new Comanda();
        comanda.setStatus(StatusComanda.ABERTO);
        comanda.setCodigo(codigoComanda);
        comanda.setFilial(objetoFilial);
        comandas.add(comanda) ;
        return comanda;
    }

    private Filial buscarFilial(String codigoFilial){
        for(Filial filial : filiais){
            if(filial.getCodigo().equals(codigoFilial)){
                return filial;
            }
        }
        return null;
    }

    public void incluirItemComanda(Integer codigoComanda, String codigoProduto, Integer quantidade) throws DadosInvalidosException {
        Comanda objetoComanda = buscarComanda(codigoComanda);
        if(objetoComanda == null){
            throw new DadosInvalidosException("Comanda não encontrada.");
        }

        if(objetoComanda.getStatus() == StatusComanda.FECHADO){
            throw new DadosInvalidosException("Comanda já fechada.");
        }

        Filial filial = objetoComanda.getFilial();
        Produto objetoProduto = getProdutoDoCardapio(codigoProduto, filial);

        if(objetoProduto == null){
            throw new DadosInvalidosException("Produto não encontrado.");
        }

        ItemComanda itemcomanda = new ItemComanda();
        itemcomanda.setProduto(objetoProduto);
        itemcomanda.setQuantidade(quantidade);
        objetoComanda.adicionarItemComanda(itemcomanda);
    }

    private Produto getProdutoDoCardapio(String codigoproduto, Filial objetofilial){
        List<ItemCardapio> cardapio = objetofilial.getCardapio();
        for (ItemCardapio itemCardapio : cardapio) {
            if (itemCardapio.getProduto().getCodigo().equals(codigoproduto)) {
                return itemCardapio.getProduto();
            }
        }
        return null;
    }

    private Comanda buscarComanda(Integer codigoComanda){
        for(Comanda comanda : comandas){
            if(comanda.getCodigo().equals(codigoComanda)){
                return comanda;
            }
        }
        return null;
    }

    public void removerItemComanda(Integer codigoComanda, String codigoProduto, Integer quantidade) throws DadosInvalidosException{
        Comanda objetoComanda = buscarComanda(codigoComanda);
        if (objetoComanda == null) {
            throw new DadosInvalidosException("Comanda não encontrada.");
        }

        if(objetoComanda.getStatus() == StatusComanda.FECHADO){
            throw new DadosInvalidosException("Comanda já fechada.");
        }
        Filial filial = objetoComanda.getFilial();
        Produto objetoProduto = getProdutoDoCardapio(codigoProduto, filial);

        if(objetoProduto == null){
            throw new DadosInvalidosException("Produto não encontrado.");
        }

        for (int i = 0; i < objetoComanda.getItems().size(); i++) {
            if (objetoComanda.getItems().get(i).getProduto().equals(objetoProduto)){
                objetoComanda.getItems().remove(i);
            }
        }

    }

    public BigDecimal totalizarComanda(Integer codigoComanda) throws DadosInvalidosException {
        Comanda objetoComanda = buscarComanda(codigoComanda);
        if (objetoComanda == null) {
            throw new DadosInvalidosException("Comanda não encontrada.");
        }
        Filial filial = buscarFilial(objetoComanda.getFilial().getCodigo());
        float resultado = 0;
        if (filial != null) {
        for (int i = 0; i < objetoComanda.getItems().size(); i++) {
                float precoProduto = filial.getPrecoProdutoCardapio(objetoComanda.getItems().get(i).getProduto());
                resultado += (precoProduto * objetoComanda.getItems().get(i).getQuantidade());
            }
        }

        return BigDecimal.valueOf(resultado);
    }

    public void fecharComanda(Integer codigoComanda) throws DadosInvalidosException{
        Comanda objetoComanda = buscarComanda(codigoComanda);
        if (objetoComanda == null) {
            throw new DadosInvalidosException("Comanda não encontrada.");
        }
        objetoComanda.setStatus(StatusComanda.FECHADO);
    }

}
