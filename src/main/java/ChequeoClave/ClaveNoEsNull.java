package ChequeoClave;

public class ClaveNoEsNull implements ChequeoDeCalidadDeClave {

	public Boolean esValida(String clave) {
		return null != clave;
	}

}
