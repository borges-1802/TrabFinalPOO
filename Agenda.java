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
	private final String arquivo = "C:\\Users\\jvbon\\OneDrive\\Área de Trabalho\\trabalho final\\contatos.txt";

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
			return false;
		}
		if(contatos.size() >= MAX){
			throw new AgendaCheiaException("Tá cheio pai");
		}
		for (int i = 0; i < contatos.size(); i++) {
            Contato contatoIgual = contatos.get(i);
				if (contatoIgual.getTelefone().equals(c.getTelefone())) {
					throw new ContatoDuplicadoException();
			}
		}	
		contatos.add(c);
		atualizarArquivo();
		return true;
	}

	public boolean remover(String nomeContato) {
		for (int i = 0; i < contatos.size(); i++) {
            Contato contato = contatos.get(i);
			if (contato.getNome().equals(nomeContato)) {
				contatos.remove(contato);
				atualizarArquivo();
				return true;
			}
		}
			return false;
	}
	
    public boolean atualizar(Contato c, String contatoNovo, String telNovo) {
        if (contatoNovo != null && !contatoNovo.isEmpty()) {
            c.setNome(contatoNovo);
        }
        if (telNovo != null && !telNovo.isEmpty()) {
        	for (int i = 0; i < contatos.size(); i++) {
                Contato contatoIgual = contatos.get(i);
                if (contatoIgual.getTelefone().equals(telNovo) && !contatoIgual.equals(c)) {
                    return false;
                }
            }
            c.setTelefone(telNovo);
        }
        atualizarArquivo();
         return true;
    }
    public List<Contato> buscar(String nome) {
        List<Contato> lista = new ArrayList<>();
        for (Contato contato : contatos) {
            if (contato.getNome().equals(nome)) {
                lista.add(contato);
            }
        }
        return lista;
}
    public List<Contato> listaContatos() {
        return new ArrayList<>(contatos);
    }
    
    private void carregarArquivo() {
    	try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
    		String linha;
    		while ((linha = br.readLine()) != null) {
    			if (linha.startsWith("Nome: [") && linha.contains("] | Tel.: [")) {
                    String[] partesNome = linha.split("\\[|\\]");
                    if (partesNome.length >= 4) {
                        String nome = partesNome[1];
                        String telefone = partesNome[3];
                        contatos.add(new Contato(nome, telefone));
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
