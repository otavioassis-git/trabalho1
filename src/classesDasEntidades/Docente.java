package classesDasEntidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import classesDasEntidades.atividades.Atividade;

public class Docente implements Serializable{
    private String login;
	private String nome;
    private String pagweb;
    public Map<String, Diciplina> dic = new HashMap<>();
    public Map<Integer, Atividade> atv = new HashMap<>();

    public Docente(String l, String n, String p){
    	login = l;
    	nome = n;
    	pagweb = p;
    }
    
    public String getLogin() {
		return login;
	}
	public String getNome() {
		return nome;
	}
	public String getPagweb() {
		return pagweb;
	}
	
	public int contaDiciplinas() {
    	int cont=0;
    	
    	for (String s : dic.keySet()) {
    		cont++;
    	}
    	return cont;
    }
    
    public int contaPeriodos() {
    	int contp=1, i=0;
        Periodo aux = null;
    	
    	for(String s : dic.keySet()) {
    		if(i==0) {
    			aux = dic.get(s).getPer();
    			i++;
    		}
    		if (!aux.getAnoSemestre().equals(dic.get(s).getPer().getAnoSemestre())) {
    			contp++;
    		}
    		aux = dic.get(s).getPer();
    	}
    	
    	if(dic.size() == 0)
    		contp--;
    	
    	return contp;
    }
    
    public double mediaAtividadesPorDiciplina() {
    	double media=0;
    	
    	for(String s : dic.keySet()) {
    		media+=dic.get(s).atv.size();
    	}
    	
    	if(media == 0)
    		return 0;
    	else
    		return media/dic.size();
    }
    
    public double percentualAtividadesSincronas() {
    	double cont=0, cont2=0;
    	
    	for (String s : dic.keySet()) {
    		for(Integer i : dic.get(s).atv.keySet()) {
    			if(dic.get(s).atv.get(i).isSinc())
    				cont++;
    		}
    	}
    	
    	cont2+=atv.size();
    	
    	if(cont2 == 0)
    		return 0;
    	else 
    		return (cont/cont2)*100;
    }
    
    

    public double mediaNotasRecebidas() {
    	int media=0, cont=0;
    	
    	for(String s : dic.keySet()) {
    		for(Integer i : dic.get(s).atv.keySet()) {
    			for(Long j : dic.get(s).atv.get(i).avaliacao.keySet()) {
    				cont++;
    				media+= dic.get(s).atv.get(i).avaliacao.get(j);
    			}
    		}
    	}
    	
    	if(media == 0)
    		return 0;
    	else
    		return (media/cont);
    	
    }
    
    public static void sortNome(ArrayList<Docente> d) {
		Docente aux;
		for(int i=0;i<d.size();i++) {
			for(int j=i;j<d.size();j++) {
				if(d.get(i).nome.compareTo(d.get(j).nome) < 0) {
					aux = d.get(i);
					d.set(i, d.get(j));
					d.set(j, aux);
				}
			}
		}
	}
}
