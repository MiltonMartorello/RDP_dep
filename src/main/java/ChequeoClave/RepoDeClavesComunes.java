package ChequeoClave;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class RepoDeClavesComunes {

	private static RepoDeClavesComunes repoDeClavesComunesInstance = new RepoDeClavesComunes();

	private static Set<String> clavesComunes;

	public static Set<String> getClavesComunes() {
		return clavesComunes;
	}

	public RepoDeClavesComunes() {
		try {
			String archivo = "common-passwords.txt";
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			File file = new File(Objects.requireNonNull(classLoader.getResource(archivo)).getFile());
			List<String> strings = Files.readAllLines(file.toPath());
			clavesComunes = new HashSet<>(strings);
		} catch (IOException e) {
			throw new CreacionDeRepoDeClavesComunesException("Hubo un problema creando el repositorio de claves", e);
		}
	}

	public static RepoDeClavesComunes getRepoDeClavesComunesInstance() {
		return repoDeClavesComunesInstance;
	}

	public Boolean esComun(String clave) {
		return clavesComunes.contains(clave);
	}

}
