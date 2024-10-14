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
    static final String URL_DOWNLOAD_XLSX = "${URL_BASE}arquivos/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-tiss/padrao-tiss-tabelas-relacionadas/Tabelaerrosenvioparaanspadraotiss__1_.xlsx"


    Document paginaPrincipal(){
        String url = URL_BASE
        Document paginaInicial = Jsoup.connect(url).get()
        return paginaInicial
    }

    Document     acessarPrestador(){

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

    Document acessarTabelasRelacionadas(){

        Element botaoTabelasRelacionadas = acessarTISS().select("a[href='$URL_TABELAS_RELACIONADAS']").first()
        if (botaoTabelasRelacionadas) {
            println "Estou acessando Tabelas Relacionadas"
            Document paginaTabelasRelacionadas = Jsoup.connect(botaoTabelasRelacionadas.attr("href")).get()
            return paginaTabelasRelacionadas
        }else {
            throw new Exception("Botão Tabelas Relacionads não encontrado.")
        }

    }

    Element botaoDownloadXlsx = acessarTabelasRelacionadas().select("a[href='$URL_DOWNLOAD_XLSX']").first()
    Element botaoDownloadZip = acessarPadraoAtual().select("a[href='$URL_DOWNLOAD_ZIP']").first()

    void baixarArquivo(String urlArquivo, String nomeArquivo, String diretorio) {
        println (acessarTabelasRelacionadas())
        try {
            if (urlArquivo) {
                Path path = Paths.get(diretorio)

                if (!Files.exists(path)) {
                    Files.createDirectories(path)
                }

                HttpBuilder.configure {
                    request.uri = urlArquivo
                }.get {
                    Download.toFile(delegate, new File("${diretorio}/${nomeArquivo}"))
                }

                println "Download concluído com sucesso e salvo em ${diretorio}/${nomeArquivo}..."
            } else {
                throw new Exception("URL de download não fornecida.")
            }
        } catch (Exception e) {
            println "Erro ao baixar o arquivo: ${e.message}"
        }
    }

    void baixarArquivoZip(String diretorio) {
        if (botaoDownloadZip) {
            String urlArquivo = botaoDownloadZip.attr("href")
            baixarArquivo(urlArquivo, "componente-comunicao.zip", diretorio)
        } else {
            println "Botão de Download ZIP não encontrado."
        }
    }

    void baixarArquivoXlsx(String diretorio) {
        if (botaoDownloadXlsx) {
            String urlArquivo = botaoDownloadXlsx.attr("href")
            baixarArquivo(urlArquivo, "tabela-erros-envio.xlsx", diretorio)
        } else {
            println "Botão de Download XLSX não encontrado."
        }
    }
}
