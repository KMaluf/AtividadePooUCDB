package atividade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;



public class Main{
	 public static void main(String[] args) {
		 String arq = "cardapio.txt";
		 int contadorPedido = 1;
		 
			String cardapiotxt = arquivo.Read(arq);
		    if(cardapiotxt.isEmpty()) {
		        System.out.println("Erro ao ler do arquivo!");
		    }
		    else {
		    	String quebra1[] = cardapiotxt.trim().split(";");
		    	Map<String,ItemMenu>listaItems= new HashMap<>();
		    	for (int i = 0; i < quebra1.length; i++) {
		    		ItemMenu menu = new ItemMenu();
		    		String [] itemSplit = quebra1[i].split("\\|"); 
		    		menu.itemNumero = itemSplit[0].trim();
		    		menu.nome = itemSplit[1].trim();
		    		menu.valor = Double.parseDouble(itemSplit[2].trim());
		    		listaItems.put(menu.itemNumero,menu);
				}
		    	boolean estaPedindo= true;
				 double vendaTotal = 0;
				 String todosPedidos= "Todos os pedidos realizados\n";
		    	while(estaPedindo) {
		    		String nome = JOptionPane.showInputDialog(null, "Qual o seu Nome?", "The title", JOptionPane.QUESTION_MESSAGE);
		    		while (nome == null || nome.isEmpty()) {
		    			nome = JOptionPane.showInputDialog(null, "É preciso informar o nome para realizar o pedido?", "The title", JOptionPane.QUESTION_MESSAGE);
		    		}
		    		boolean adicionandoItem = true;
		    		List<String> lanchePedido = new ArrayList<String>();
		    		double totalPedido = 0;
		    		while(adicionandoItem) {
		    			String pedido = JOptionPane.showInputDialog(cardapiotxt + " Informe o número do lanche dezejado?");
		    			if(pedido != null && pedido.matches("[0-9]+")) {
		    				if( listaItems.containsKey(pedido)) {
			    				lanchePedido.add(listaItems.get(pedido).nome);
			    				totalPedido += listaItems.get(pedido).valor;
			    				String continuarPedido = JOptionPane.showInputDialog("1- Continuar pedido."+ "\n2-Finalizar pedido.");
			    				int continuarPedidoInt = Integer.parseInt(continuarPedido);
			    				if(continuarPedidoInt == 2) {
			    					adicionandoItem = false;
			    				}
		    			}
		    			else {System.out.println("Opção escolhida inesistente!");}
		    			
		    		};
		    		
		        }
		    		
		    		String pedidoFinalizado = contadorPedido + " | "+ nome + " | "+ lanchePedido + " | " + totalPedido;
	    			System.out.println("Pedido finalizado: " + pedidoFinalizado);
	    			arquivo.Write("pedido "+ nome +".txt", pedidoFinalizado);
	    			todosPedidos += pedidoFinalizado+"\n";
	    			vendaTotal += totalPedido;
	    			System.out.println(todosPedidos);
	    			contadorPedido ++;
		    	String encerrar = JOptionPane.showInputDialog("1- Novo pedido."+ "\n2-Finalizar serviço.");
		    	boolean encerraIsNumber = encerrar.matches("[0-9]+");
		    	if(encerrar == null || encerraIsNumber == false) {
		    		encerrar = "2";
		    	}
		    	int encerrarInt = Integer.parseInt(encerrar);
		    	if (encerrarInt == 2) {
		    		estaPedindo=false;
		    	}
		  }
		    	todosPedidos +="Valor total vendido: R$" + vendaTotal;
    		arquivo.Write("PedidosRealizados.txt", todosPedidos);
		   System.out.println("serviço encerrado"); 	
	 }
	 }
}
	
