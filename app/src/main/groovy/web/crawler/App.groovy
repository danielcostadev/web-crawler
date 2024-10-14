package web.crawler

class App {

    static void main(String[] args) {

        Webcrawler crawler = new Webcrawler()

        crawler.baixarArquivoZip("Downloads")
        crawler.criarArquivoCSV("CSV", crawler.dadosCompetencias)
        crawler.baixarArquivoXlsx("Downloads")
        
    }

}