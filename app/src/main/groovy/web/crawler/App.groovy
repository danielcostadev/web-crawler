package web.crawler

class App {

    static void main(String[] args) {

        Webcrawler crawler = new Webcrawler()

        //crawler.paginaPrincipal()
        //crawler.acessarPrestador()
        //crawler.acessarTISS()
        //crawler.acessarPadraoAtual()
        crawler.baixarArquivoZip("Downloads")
        crawler.baixarArquivoXlsx("Downloads")
        crawler.coletarDadosTabela()

    }

}