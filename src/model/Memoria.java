package model;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private static final Memoria instancia = new Memoria();

	private final List<MemoriaObservador> observadores = new ArrayList<>();

	private Memoria() {

	}

	private EventosCalc ultimaOperacao = null;
	private boolean substituir = false;
	private String textoMemoria = "";
	private String textoAtual = "";

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}

	public static Memoria getInstancia() {
		return instancia;
	}

	public void adicionarObservador(MemoriaObservador observador) {
		observadores.add(observador);
	}

	public void processarComando(String valor) {
		EventosCalc evento = detectarQualEvento(valor);

		if (evento == null) {
			return;
		} else if (evento == EventosCalc.LIMPARULTIMO) {
			textoAtual = "";
		} else if (evento == EventosCalc.APAGAR) {
			int atual = textoAtual.length();
			textoAtual = atual > 0 ? textoAtual.substring(0, (atual - 1)) : textoAtual;
			substituir = false;
		} else if (evento == EventosCalc.SINAL) {
			if(textoAtual.contains("-")) {
				textoAtual = textoAtual.replace("-", "");
			} else {
				textoAtual = "-"+ textoAtual;				
			}

		} else if (evento == EventosCalc.LIMPARTUDO) {
			ultimaOperacao = null;
			substituir = false;
			textoMemoria = "";
			textoAtual = "";
		} else if (evento == EventosCalc.NUMERO || evento == EventosCalc.VIRGULA) {
			textoAtual = substituir ? valor : textoAtual + valor;
			substituir = false;
		} else {
			substituir = true;
			textoAtual = getResultadoOperacao();
			textoMemoria = textoAtual;
			ultimaOperacao = evento;
		}
		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
	}

	private String getResultadoOperacao() {
		if (ultimaOperacao == null || ultimaOperacao == EventosCalc.RESULTADO)
			return textoAtual;

		double numeroMemoria = Double.parseDouble(textoMemoria.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));

		double resultado = 0;

		if (ultimaOperacao == EventosCalc.SOMAR) {
			resultado = numeroMemoria + numeroAtual;
		} else if (ultimaOperacao == EventosCalc.DIMINUIR) {
			resultado = numeroMemoria - numeroAtual;
		} else if (ultimaOperacao == EventosCalc.DIVIDIR) {
			resultado = numeroMemoria / numeroAtual;
		} else if (ultimaOperacao == EventosCalc.MULTIPLICAR) {
			resultado = numeroMemoria * numeroAtual;
		}

		String resuladoFim = Double.toString(resultado);

		return Operacao.verifica(resuladoFim).replace(".", ",");
	}

	private EventosCalc detectarQualEvento(String valor) {

		if (valor.equals("0") && getTextoAtual().equals("0")) {
			return null;
		}
		try {
			Integer.parseInt(valor);
			return EventosCalc.NUMERO;
		} catch (NumberFormatException e) {
			if (valor.equals("+")) {
				return EventosCalc.SOMAR;
			} else if (valor.equals("-")) {
				return EventosCalc.DIMINUIR;
			} else if (valor.equals("X")) {
				return EventosCalc.MULTIPLICAR;
			} else if (valor.equals("/")) {
				return EventosCalc.DIVIDIR;
			} else if (valor.equals("CE")) {
				return EventosCalc.LIMPARTUDO;
			} else if (valor.equals("C")) {
				return EventosCalc.LIMPARULTIMO;
			} else if (valor.equals(",") && !textoAtual.contains(",")) {
				return EventosCalc.VIRGULA;
			} else if (valor.equals("<--")) {
				return EventosCalc.APAGAR;
			} else if (valor.equals("=")) {
				return EventosCalc.RESULTADO;
			} else if (valor.equals("+/-")) {
				return EventosCalc.SINAL;
			}
		}
		return null;
	}

}
