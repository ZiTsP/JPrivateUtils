package zitsp.putils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class CodingLanguage {

	private final String language;
	private final List<String> extension;

	private CodingLanguage(String language, List<String> extension) {
        this.language = language;
        this.extension = extension;
	}

	public final String getLanguage() {
		return this.language;
	}

	public final String toString() {
		StringBuffer strings = new StringBuffer();
		strings.append(this.language);
		strings.append(" [");
		for (String entry : this.extension) {
			strings.append(entry);
			strings.append(" , ");
		}
		strings.delete(strings.length() - 3, strings.length() - 1);
		strings.append("]");
		return strings.toString();
	}

	public final List<String> getExtension() {
		return extension;
	}

	public static final CodingLanguage JAVA;
//	public static final CodingLanguage BAR;
	static {
		List<String> tmpExtention = new ArrayList<>();
		tmpExtention.add(".java");
		JAVA = new CodingLanguage("Java", tmpExtention);
//		tmpExtention = new ArrayList<>();
//		tmpExtention.add(".bar1");
//		tmpExtention.add(".bar2");
//		BAR = new CodingLanguage("Bar", tmpExtention);
	}

	private static final List<CodingLanguage> languageList = new ArrayList<>();
	static {
		languageList.add(JAVA);
	}

	public static final List<CodingLanguage> getAll() {
		return languageList;
	}

	public static Optional<CodingLanguage> getLanguage(String extension) {
		if (extension == null) {
			throw new NullPointerException();
		}
		return languageList.stream().filter(e -> e.extension.contains(extension)).findAny();
//		for (CodingLanguage entry : languageList) {
//			if (entry.extension.contains(extension)) {
//				return Optional.of(entry);
//			}
//		}
//		return Optional.empty();
	}

	public static List<String> getExtension(String language) {
		for (CodingLanguage entry : languageList) {
			if (entry.language.equals(language)) {
				return entry.extension;
			}
		}
		return new ArrayList<>();
	}

	public static Optional<CodingLanguage> parse(String language) {
		if (language == null) {
			throw new NullPointerException();
		}
		return languageList.stream()
				.filter(e -> e.language.toLowerCase().equals(language.toLowerCase())).findFirst();
	}

	public static boolean has(String language) {
		if (language != null && parse(language).isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean has(CodingLanguage language) {
		if (language != null && CodingLanguage.languageList.contains(language)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(CodingLanguage.languageList.get(0).toString());
	}

}
