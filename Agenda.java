package TrabalhoFinal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

	private List<Contato> contatos;
	public final int MAX = 1000; //limite de 1000 contatos
	private final String arquivo = "C:\\Users\\jvbon\\OneDrive\\Área de Trabalho\\trabalho final\\contatos.txt"; //diretório do arquivo texto

	// Singleton
	private static Agenda instance;
	private Agenda(){
		contatos = new ArrayList<Contato>();
		carregarArquivo();
	}
	
	public static Agenda getAgenda(){
		if (instance == null)
		instance = new Agenda();
		return instance;
	}
	
	public boolean adicionar(Contato c) throws ContatoDuplicadoException, AgendaCheiaException {
		if(c.getNome() == null || c.getNome().isEmpty() || c.getTelefone() == null || c.getTelefone().isEmpty()) {
			return false; //se nome ou numero estiverem vazios, retorna falso
		}
		if(contatos.size() >= MAX){
			throw new AgendaCheiaException("Tá cheio pai"); //se for maior que 1000, lançada a exceção checked
		}
		for (int i = 0; i < contatos.size(); i++) {
            Contato contatoIgual = contatos.get(i);
				if (contatoIgual.getTelefone().equals(c.getTelefone())) {
					throw new ContatoDuplicadoException(); //se o numero for duplicado, lança a exceção checked
			}
		}	
		contatos.add(c); //se não, adicionar a agenda
		atualizarArquivo(); //e atualizar no arquivo
		return true; //retornando verdadeiro
	}

	public boolean remover(String nomeContato) {
		for (int i = 0; i < contatos.size(); i++) {
            Contato contato = contatos.get(i); //contador da agenda, percorrendo contato por contato
			if (contato.getNome().equals(nomeContato)) { //se o contato for igual ao contato digitado
				contatos.remove(contato); //ele remove da agenda
				atualizarArquivo(); //e eh atulizado no arquivo
				return true; //retornando verdadeiro ao remover
			}
		}
			return false; //ou falso caso nao exista
	}
	
    public boolean atualizar(Contato c, String contatoNovo, String telNovo) {
        if (contatoNovo != null && !contatoNovo.isEmpty()) {
            c.setNome(contatoNovo); //se o nome for digitado, seta o nome do contato
        }
        if (telNovo != null && !telNovo.isEmpty()) {
        	for (int i = 0; i < contatos.size(); i++) {
                Contato contatoIgual = contatos.get(i); //contator da agenda, percorrendo contato por contato
                if (contatoIgual.getTelefone().equals(telNovo) && !contatoIgual.equals(c)) { //se o numero for igual ao antigo
                    return false; //retorna falso
                }
            }
            c.setTelefone(telNovo); //se não atualiza na agenda
        }
        atualizarArquivo(); //e no arquivo
         return true; //retornando verdadeiro
    }
    public List<Contato> buscar(String nome) {
        List<Contato> lista = new ArrayList<>(); //cria uma lista com o nome pesquisado
        for (Contato contato : contatos) {
            if (contato.getNome().equals(nome)) {
                lista.add(contato); //vai correndo a agenda ate aparecer o nome pesquisado e inclue na lista de busca
            }
        }
        return lista; //retorna a busca
}
    public List<Contato> listaContatos() {
        return new ArrayList<>(contatos); //cria uma lista com os contatos salvos
    }
    
    private void carregarArquivo() {
    	try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) { // cria um leitor de arquivo eficiente atraves da criação de um novo leitor de arquivo específico
    		String linha;
    		while ((linha = br.readLine()) != null) { //le a linha ate pular
    			if (linha.startsWith("Nome: [") && linha.contains("] | Tel.: [")) { //verifica se possui os atributos do contato
                    String[] partesNome = linha.split("\\[|\\]"); //dividindo em partes específicas
                    if (partesNome.length >= 4) { //arumando em diferentes vetores divididos
                        String nome = partesNome[1];
                        String telefone = partesNome[3];
                        contatos.add(new Contato(nome, telefone)); //adiconando na lista após leitura e escrita
                    }
    			
    			}	
    		}
    	} catch(IOException e) {
    		System.out.println("Erro de I/O: " + e);
    	}
    }

    private void atualizarArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write("====== Lista de Contatos =======\n");
        	for (int i = 0; i < contatos.size(); i++) {
                Contato contato = contatos.get(i);
                bw.write("Nome: [" + contato.getNome() + "] | Tel.: [" + contato.getTelefone() + "]");
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o arquivo de contatos: " + e.getMessage());
        }
    }    
} 
