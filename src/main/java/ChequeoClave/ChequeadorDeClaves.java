package ChequeoClave;

import java.util.Arrays;
import java.util.List;

public class ChequeadorDeClaves {

	private static ChequeadorDeClaves chequeadorDeClaves = new ChequeadorDeClaves();

	private List<ChequeoDeCalidadDeClave> chequeoDeCalidadDeClave;

	private ChequeadorDeClaves() {
		chequeoDeCalidadDeClave = Arrays.asList(
				new ClaveNoEsNull(),
				new NoEsClaveComun(),
				new MinimaCantidadDeCaracteres()
		);
	}

	public static ChequeadorDeClaves getChequeadorDeClavesInstance() {
		return chequeadorDeClaves;
	}

	public Boolean esClaveSegura(String clave) {
		return chequeoDeCalidadDeClave.stream().allMatch(chequeo -> chequeo.esValida(clave));
	}

}
