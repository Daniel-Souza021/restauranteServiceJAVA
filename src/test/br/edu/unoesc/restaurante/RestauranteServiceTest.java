package br.edu.unoesc.restaurante;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class RestauranteServiceTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    /*
     *  Deve abrir comanda inserindo produto e totalizando comanda restaurante
     *
     * */
    @Test
    public void deveAbrirComandaInserindoProdutoETotalizandoComandaRestaurante() throws DadosInvalidosException {
        RestauranteService restauranteService = new RestauranteService(gerarListaFiliais());

        Comanda comanda = restauranteService.abrirComanda("R1");

        restauranteService.incluirItemComanda(comanda.getCodigo(), "P1", 2);

        BigDecimal totalComanda = restauranteService.totalizarComanda(comanda.getCodigo());

        assertEquals(BigDecimal.valueOf(50.0), totalComanda);
    }

    /*
     *  Deve abrir comanda inserindo produto e totalizando comanda shopping
     *
     * */
    @Test
    public void deveAbrirComandaInserindoProdutoETotalizandoComandaShopping() throws DadosInvalidosException {
        RestauranteService restauranteService = new RestauranteService(gerarListaFiliais());

        Comanda comanda = restauranteService.abrirComanda("S1");

        restauranteService.incluirItemComanda(comanda.getCodigo(), "B1", 2);

        BigDecimal totalComanda = restauranteService.totalizarComanda(comanda.getCodigo());

        assertEquals(BigDecimal.valueOf(12.0), totalComanda);
    }

    /*
     *  Deve abrir comanda inserindo, removendo produto e totalizando comanda
     *
     * */
    @Test
    public void deveAbrirComandaInserindoERemovendoProdutoETotalizandoComanda() throws DadosInvalidosException {
        RestauranteService restauranteService = new RestauranteService(gerarListaFiliais());

        Comanda comanda = restauranteService.abrirComanda("R1");

        restauranteService.incluirItemComanda(comanda.getCodigo(), "P1", 2);
        restauranteService.incluirItemComanda(comanda.getCodigo(), "B1", 2);

        restauranteService.removerItemComanda(comanda.getCodigo(), "B1", 2);

        BigDecimal totalComanda = restauranteService.totalizarComanda(comanda.getCodigo());
        assertEquals(BigDecimal.valueOf(50.0), totalComanda);
    }

    /*
     *  Deve abrir duas comandas inserindo produtos etotalizando ambas
     *
     * */
    @Test
    public void deveAbrirDuasComandasInserindoProdutosETotalizandoAmbas() throws DadosInvalidosException {
        RestauranteService restauranteService = new RestauranteService(gerarListaFiliais());

        Comanda comanda1 = restauranteService.abrirComanda("R1");
        restauranteService.incluirItemComanda(comanda1.getCodigo(), "P1", 2);
        restauranteService.incluirItemComanda(comanda1.getCodigo(), "B1", 2);
        BigDecimal totalComanda1 = restauranteService.totalizarComanda(comanda1.getCodigo());

        Comanda comanda2 = restauranteService.abrirComanda("R1");
        restauranteService.incluirItemComanda(comanda2.getCodigo(), "P1", 1);
        restauranteService.incluirItemComanda(comanda2.getCodigo(), "B1", 1);
        BigDecimal totalComanda2 = restauranteService.totalizarComanda(comanda2.getCodigo());


        assertEquals(BigDecimal.valueOf(62), totalComanda1);
        assertEquals(BigDecimal.valueOf(37), totalComanda2);
    }

    /*
     *  Deve retornar DadosInvalidosException caso o produto nao esteja cadastrado
     *
     * */
    @Test
    public void deveRetornarDadosInvalidosExceptionCasoOProdutoNaoEstejaCadastrado() throws DadosInvalidosException {
        exceptionRule.expect(DadosInvalidosException.class);
        exceptionRule.expectMessage("Produto não encontrado");

        RestauranteService restauranteService = new RestauranteService(gerarListaFiliais());

        Comanda comanda = restauranteService.abrirComanda("R1");

        restauranteService.incluirItemComanda(comanda.getCodigo(), "P99", 2);
    }

    /*
     *  Deve retornar DadosInvalidosException caso não exista comanda para o codigo informado
     *
     * */
    @Test
    public void deveRetornarDadosInvalidosExceptionCasoNaoExistaComandaParaOCodigoInformado() throws DadosInvalidosException {
        exceptionRule.expect(DadosInvalidosException.class);
        exceptionRule.expectMessage("Comanda não encontrada");

        RestauranteService restauranteService = new RestauranteService(gerarListaFiliais());
        restauranteService.incluirItemComanda(9999, "P99", 2);
    }

    /*
     *  Deve retornar DadosInvalidosException caso seja inserido produto e comanda fechada
     *
     * */
    @Test
    public void deveRetornarDadosInvalidosExceptionCasoSejaInseridoProdutoEComandaFechada() throws DadosInvalidosException {
        exceptionRule.expect(DadosInvalidosException.class);
        exceptionRule.expectMessage("Comanda já fechada");

        RestauranteService restauranteService = new RestauranteService(gerarListaFiliais());
        Comanda comanda = restauranteService.abrirComanda("R1");
        restauranteService.fecharComanda(comanda.getCodigo());

        restauranteService.incluirItemComanda(comanda.getCodigo(), "P99", 2);
    }

    /*
     *  Deve retornar DadosInvalidosException ao tentar totalizar comanda inexistente
     *
     * */
    @Test
    public void deveRetornarDadosInvalidosExceptionAoTentarTotalizarComandaInexistente() throws DadosInvalidosException {
        exceptionRule.expect(DadosInvalidosException.class);
        exceptionRule.expectMessage("Comanda não encontrada");

        RestauranteService restauranteService = new RestauranteService(gerarListaFiliais());
        restauranteService.totalizarComanda(9999);
    }

    /*
     *  Deve retornar DadosInvalidosException ao tentar fechar comanda inexistente
     *
     * */
    @Test
    public void deveRetornarDadosInvalidosExceptionAoTentarFecharComandaInexistente() throws DadosInvalidosException {
        exceptionRule.expect(DadosInvalidosException.class);
        exceptionRule.expectMessage("Comanda não encontrada");

        RestauranteService restauranteService = new RestauranteService(gerarListaFiliais());
        restauranteService.fecharComanda(9999);
    }


    /* --- Outros testes que julgar necessário --- */
    // TODO Escreva aqui outros testes caso julgue necessário


    /* --- Métodos auxiliares para os testes --- */
        private List<Produto> gerarListaDeProdutos(){
        List<Produto> produtos = new ArrayList();

        Produto macarrao = new Produto();
        macarrao.setCodigo("P1");
        macarrao.setNome("Macarrão da Casa");
        macarrao.setDescricao("Macarrão ao molho bolonhesa");
        macarrao.setCategoria(CategoriaEnum.PRATO);

        Produto refrigerante = new Produto();
        refrigerante.setCodigo("B1");
        refrigerante.setNome("Guaraná");
        refrigerante.setDescricao("Guaraná 350ml");
        refrigerante.setCategoria(CategoriaEnum.BEBIDA);

        produtos.add(macarrao);
        produtos.add(refrigerante);
        return produtos;
    }

    private List<ItemCardapio> gerarCardapio(List<Produto> produtos) {
        List<ItemCardapio> cardapio = new ArrayList();
        produtos.forEach(produto -> {
            ItemCardapio itemCardapio = new ItemCardapio();
            itemCardapio.setProduto(produto);
            itemCardapio.setPreco(CategoriaEnum.PRATO.equals(produto.getCategoria()) ? BigDecimal.valueOf(25) : BigDecimal.valueOf(6));
            cardapio.add(itemCardapio);
        });

        return cardapio;
    }

    private List<Filial> gerarListaFiliais(){
        List<Filial> filiais = new ArrayList<>();
        List<ItemCardapio> cardapio = gerarCardapio(gerarListaDeProdutos());

        Filial restaurante = new Filial();
        restaurante.setCodigo("R1");
        restaurante.setNome("Restaurante Centro");
        restaurante.setCardapio(cardapio);
        filiais.add(restaurante);

        Filial shopping = new Filial();
        shopping.setCodigo("S1");
        shopping.setNome("Unidade Shopping");
        shopping.setCardapio(cardapio);
        filiais.add(shopping);

        return filiais;
    }

}
