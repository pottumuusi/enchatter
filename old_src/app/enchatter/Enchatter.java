package enchatter;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Enchatter {
	private static final boolean DEBUG = true;
	private static final int CONF_PATH_ARG = 0;

	public static void main(String[] args) {
		parseArgs(args);

		Configuration configuration = new Configuration();
		String fetchedTag = configuration.TAG;

		System.out.println("Fetched entry is: " + fetchedTag);
	}

	/* Only expect path to configuration to use. */
	private static void parseArgs(String[] args) {
		if (args.length != 1) {
			return;
		}

		String rawPath = args[CONF_PATH_ARG];
		Path confPath = rawPathToPath(rawPath);

		// URI confUri = pathToUri(confPath);
	}

	private static Path rawPathToPath(String rawPath) {
		Path path = Paths.get(rawPath);
		String filename = path.getFileName().toString();

		// if (!path.isAbsolute()) {
		// 	// only absolute path leading to configuration is supported
		// 	throw here
		// }

		if (DEBUG) System.out.println("rawPathToPath, created path with filename: " + filename);

		return path;
	}

	private static URI pathToUri(Path confPath) throws Exception {
		String filename = confPath.getFileName().toString();
		if (DEBUG) System.out.println("pathToUri, creating uri from filename: " + filename);

		throw new Exception("Not yet implemented");

		// return null;
	}

	private static void readConfigurationFromFile() {
	}
}
