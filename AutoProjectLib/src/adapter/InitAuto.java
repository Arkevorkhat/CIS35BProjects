package adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;

import data.Auto;

public class InitAuto extends ProxyAuto {
	public static File getBinFile() {
		try {
			File f = new File(Auto.class.getResource("store.bin").toURI());
			if (!f.exists()) {
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			return f;
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void init() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getBinFile()));
			autoMap = LinkedHashMap.class.cast(ois.readObject()); // there is no good way to get the hashmap from file
																	// without this warning. there's 0 chance that it
																	// will grab something invalid.

			ois.close();
		} catch ( IOException | ClassNotFoundException | ClassCastException e) {
			e.printStackTrace();
			System.err.println("Failed to complete initialization tasks, exiting...");
			System.exit(4);
		}
	}
}
