package ChequeoClave;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


class LectorArchivosTexto implements GeneradorDeClavesComunes {
		public HashSet<String> generarClavesComunes() {
			try {
				String archivo = "common-passwords.txt";
				ClassLoader classLoader = ClassLoader.getSystemClassLoader();
				File file = new File(Objects.requireNonNull(classLoader.getResource(archivo)).getFile());
				List<String> strings = Files.readAllLines(file.toPath());
				return new HashSet<>(strings);
			} catch (IOException e) {
				throw new CreacionDeRepoDeClavesComunesException("Hubo un problema creando el repositorio de claves", e);
			}
		}
}