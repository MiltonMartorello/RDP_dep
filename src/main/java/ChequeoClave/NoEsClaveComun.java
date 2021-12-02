package ChequeoClave;

import java.util.Set;

public class NoEsClaveComun implements ChequeoDeCalidadDeClave {
	private Set<String> clavesComunes;
	private GeneradorDeClavesComunes generadorDeClavesComunes = new LectorArchivosTexto();

	public NoEsClaveComun() {
		clavesComunes = generadorDeClavesComunes.generarClavesComunes();
	}

	public void setGeneradorDeClavesComunes(GeneradorDeClavesComunes generadorDeClavesComunes) {
		this.generadorDeClavesComunes = generadorDeClavesComunes;
	}

	//Considerar separar responsabilidades, getClavesComunes dos acciones validar, y la otra es leer el archivo.

	public Boolean esValida(String clave) {
		return !clavesComunes.contains(clave);
	}
	public Set<String> getClavesComunes(){
		return clavesComunes;
	}
}


