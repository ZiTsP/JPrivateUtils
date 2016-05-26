package zitsp.putils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathRename {
	private PathRename() {
	}

	public static Path renamedPath(Path oldPath, String prefix, String suffix) {
		if (oldPath == null || Files.exists(oldPath)) {
			throw new NullPointerException();
		}
		String parent = oldPath.getParent().toAbsolutePath().toString();
		String extension = getExtension(oldPath);
		StringBuffer newName = new StringBuffer();
		if (prefix != null && !prefix.equals("")) {
			newName.append(prefix);
		}
		newName.append(getNameWithouExtension(oldPath, extension));
		if (suffix != null && !suffix.equals("")) {
			newName.append(suffix);
		}
		if (extension != null && !extension.equals("")) {
			newName.append(extension);
		}
		return Paths.get(parent, newName.toString());
	}

	private static String getExtension(Path path) {
		String name = path.getFileName().toString();
		int index = name.lastIndexOf(".");
		if (index > 0) {
			return name.substring(index, name.length());
		} else {
			return "";
		}
	}

	private static String getNameWithouExtension(Path path, String extension) {
		String name = path.getFileName().toString();
		if (extension == null || extension.equals("")) {
			return name;
		}
		int index = name.lastIndexOf(extension);
		if (index > 0) {
			return name.substring(0, index);
		} else {
			return "";
		}
	}
}
