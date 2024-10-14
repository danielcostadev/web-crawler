package web.crawler

import groovyx.net.http.HttpBuilder
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import groovyx.net.http.optional.Download
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class Webcrawler {

    static final String URL_BASE = "https://www.gov.br/ans/pt-br/"
    static final String URL_PRESTADOR = "${URL_BASE}assuntos/prestadores"
    static final String URL_TISS = "${URL_PRESTADOR}/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss"
    static final String URL_PADRAO_ATUAL = "${URL_PRESTADOR}/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/setembro-2024"
    static final String URL_TABELAS_RELACIONADAS = "${URL_PRESTADOR}/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-tabelas-relacionadas"
    static final String URL_DOWNLOAD_ZIP = "${URL_PRESTADOR}/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/PadroTISSComunicao202301.zip"
    static final String URL_DOWNLOAD_XLSX = "${URL_PRESTADOR}/padrao-para-troca-de-informacao-de-saude-suplementar-tiss/padrao-tiss-tabelas-relacionadas/Tabelaerrosenvioparaanspadraotiss__1_.xlsx"

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
            return paginaPrestador
        } else {
            throw new Exception("Botão Prestador não encontrado.")
        }
    }

    Document acessarTISS(){
        Element botaoTISS = acessarPrestador().select("a[href='$URL_TISS']").first()
        if (botaoTISS) {
            println "Estou acessando TISS"
            Document paginaTISS = Jsoup.connect(botaoTISS.attr("href")).get()
            return paginaTISS
        }else {
            throw new Exception("Botão TISS não encontrado.")
        }
    }

    Document acessarPadraoAtual(){

        Element botaoPadraoAtual = acessarTISS().select("a[href='$URL_PADRAO_ATUAL']").first()
        if (botaoPadraoAtual) {
            println "Estou acessando Padrao Atual"
            Document paginaPadaoAtual = Jsoup.connect(botaoPadraoAtual.attr("href")).get()
            return paginaPadaoAtual
        }else {
            throw new Exception("Botão Padrao Atual não encontrado.")
        }

    }



    void baixarArquivoZip(String tipo, String diretorio) {

        if (tipo == 'zip'){

        }

        try {
            Element botaoDownloadZip = acessarPadraoAtual().select("a[href='$URL_DOWNLOAD_ZIP']").first()
            if (botaoDownloadZip) {
                String urlArquivo = botaoDownloadZip.attr("href")

                Path path = Paths.get(diretorio)
                if (!Files.exists(path)) {
                    Files.createDirectories(path)
                }

                File file = HttpBuilder.configure {
                    request.uri = urlArquivo
                }.get {
                    Download.toFile(delegate, new File("${diretorio}/componente-comunicao.zip"))
                }

                println "Download concluído com sucesso e salvo em ${diretorio}..."
            } else {
                throw new Exception("Botão de Download ZIP não encontrado.")
            }
        } catch (Exception e) {
            println "Erro ao baixar o arquivo: ${e.message}"
        }
    }

}
