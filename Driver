package TrabalhoFinal;
import java.util.List;
import java.util.Scanner;

public class Driver {
	private static final Scanner scanner = new Scanner(System.in);
	
	private static void adicionarContato(Agenda agenda) {
		System.out.println("Digite o nome do contato:");
		String nome = scanner.nextLine();
		System.out.println("Digite o número do Telefone");
		String telefone = scanner.nextLine();
		
		try {
			Contato contatoNovo = new Contato(nome, telefone);
			if(agenda.adicionar(contatoNovo))
				System.out.println("Contato adicionado!");
			else
				System.out.println("Não foi possível adicionar o contato.");	
		}
		catch(ContatoDuplicadoException e){
			System.out.println("Erro: Contato duplicado");
		}
		catch(AgendaCheiaException e) {			
			System.out.println("Erro: Agenda Cheia");
		}
	}
	
	private static void removerContato(Agenda agenda) {
		System.out.println("Digite o nome do contato que deseja remover:");
		String nome = scanner.nextLine();
		if(agenda.remover(nome))
			System.out.println("Contato removido!");
		else
			System.out.println("Não foi possível remover o contato.");
		}
	
	private static void editarContato(Agenda agenda) {
		System.out.println("Digite o nome do contato que deseja editar:");
		String nome = scanner.nextLine();
		List<Contato> contatosBuscados = agenda.buscar(nome);
		
		if(contatosBuscados.isEmpty())
			System.out.println("Tem ninguém aqui não meu rei...Lista vazia.");
		else {
			Contato contato = contatosBuscados.get(0);
			System.out.println("O contato é: " + contato.getNome() + " - " + contato.getTelefone());
			System.out.println("Digite o novo nome e novo numero para o contato (Ou aperte 'Enter' para : manter assim... \n");
			String nomeAtualizado = scanner.nextLine();
			String telAtualizado = scanner.nextLine();
			if(nomeAtualizado.isEmpty())
				nomeAtualizado = null;
			if(telAtualizado.isEmpty())
				telAtualizado = null;
			if (agenda.atualizar(contato, nomeAtualizado, telAtualizado))
				System.out.print("Atualizado com sucesso!");
			else
				System.out.println("Erro ao salvar contato...");
			}
}
				
		private static void buscarContato(Agenda agenda) {
			String nome = scanner.nextLine();
			List<Contato> contatosBuscados = agenda.buscar(nome);
			
			if (contatosBuscados.isEmpty())
				System.out.print("Nenhum contato buscado!");
			else {
				for(int i = 0; i < contatosBuscados.size(); i++) {	
				Contato contato = contatosBuscados.get(i);
				System.out.println("Nome: [" + contato.getNome() + "] | Tel.: [" + contato.getTelefone() + "]");
				}
			}
		}
		
		private static void listarContatos(Agenda agenda) {
			List<Contato> contatos = agenda.listaContatos();
			if(agenda.listaContatos().isEmpty())
				System.out.println("Nenhum contato encontrado");
			else {
				for(int i = 0; i < contatos.size(); i++) {
					Contato contato = contatos.get(i);
					System.out.print("Nome: [" + contato.getNome() + "] | Tel.: [" + contato.getTelefone() + "]\n");
				}
			}
			
		}
	
	public static void main(String args[]) {
	Agenda agenda = Agenda.getAgenda();
	boolean executar = true; 
	
		while(executar) {
			System.out.println("\n======= Lista de Contatos =======");
	        System.out.println("1. Adicionar Contato");
	        System.out.println("2. Remover Contato");
	        System.out.println("3. Editar Contato");
	        System.out.println("4. Buscar Contato");
	        System.out.println("5. Listar Contatos");
	        System.out.println("6. Encerrar");
	        System.out.print("Escolha uma opção: ");
	        String opcao = scanner.nextLine();
	        
	        switch(opcao){
	        case "1":
	        	adicionarContato(agenda);
	        	break;
	        case "2":
	        	removerContato(agenda);
	        	break;
	        case "3":
	        	editarContato(agenda);
	        	break;
	        case "4":
	        	buscarContato(agenda);
	        	break;
	        case "5":
	        	listarContatos(agenda);
	        	break;
	        case "6":
	        	System.out.println("Programa Finalizado");
	        	executar = false;
	        	break;
	        default:
	        	System.out.println("Opção inválida.");
	        }		
		}	
scanner.close();
}
	}
