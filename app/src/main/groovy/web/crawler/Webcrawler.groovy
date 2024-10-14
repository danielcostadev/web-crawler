package web.crawler

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class Webcrawler {

    static final String URL_BASE = "https://www.gov.br/ans/pt-br/"
    static final String URL_PRESTADOR = "${URL_BASE}assuntos/prestadores"
    static final String URL_TISS = "${URL_BASE}assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss"
    static final String URL_PADRAO_ATUAL = "${URL_BASE}assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/setembro-2024"
    static final String URL_DOWNLOAD = "${URL_BASE}assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/PadroTISSComunicao202301.zip"

    Document paginaPrincipal(){
        String url = URL_BASE
        Document paginaInicial = Jsoup.connect(url).get()
        return paginaInicial
    }

    Document acessarPrestador(){

        Element botaoPrestador = paginaPrincipal().select("a[href='$URL_PRESTADOR']").first()
        if (botaoPrestador) {
            println "Estou acessando Prestador!"
            Document paginaPrestador = Jsoup.connect(botaoPrestador.attr("href")).get()
            //return print(paginaPrestador)
        } else {
            throw new Exception("Botão Prestador não encontrado.")
        }
    }

    Document acessarTISS(){
        Element botaoTISS = paginaPrincipal().select("a[href='$URL_TISS']").first()
        if (botaoTISS) {
            println "Estou acessando TISS"
            Document paginaTISS = Jsoup.connect(botaoTISS.attr("href")).get()
            return print(paginaTISS)
        }else {
            throw new Exception("Botão TISS não encontrado.")
        }
    }

}
