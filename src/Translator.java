import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Translator {

	// use a map data structure to link 
	// language names to the language object
	private Map<String, Language> langs;
	private String fileName;
	
	public Translator(String fileName) {
		langs = new TreeMap<>();
		this.fileName = fileName;
		load();
	}
	
	// abstracts the process of translating text, very readable to outside
	public String translateWords(	String words, String inLang,
									String outLang) {
		words = words.toLowerCase();
		String trans = "";
		// translates every word in the text individually
		for(String word : words.split(" ")) {
			// makes the translation process seem relatively noncomplex
			trans += translate(word, inLang, outLang) + " ";
		}
		return trans;
	}
	
	public String translate(	String word, String inLang,
							String outLang) {
		word = word.toLowerCase();
		if(!langs.get(inLang).contains(word)) {
			return word;
		}
		Language l = langs.get(outLang);
		String trans = l.get(langs.get(inLang).indexOf(word));
		if(trans.equals("?")) {
			return word;
		}
		return trans;
	}
	
	// use of control structures
	public void edit(	String word, String replacement,
						String inLang, String outLang) {
		Language in = langs.get(inLang);
		// only if the input language does not have the word
		if(!in.contains(word)) {
			in.add(word);
			// loop through all languages in the Map
			for(String langName : langs.keySet()) {
				// if the language is not the input language
				if(!langName.equals(inLang)) {
					langs.get(langName).add("?");
				}
			}
		}
		// only if the output language is set to "delete"
		if(outLang.equals("delete")) {
			for(String langName : langs.keySet()) {
				if(!langName.equals(inLang)) {
					Language l = langs.get(langName);
					l.removeTranslation(in.indexOf(word));
				}
			}
			in.removeTranslation(in.indexOf(word));
			save();
			// return to stop execution of code below
			return;
		}
		// only executes if output is not "delete"
		Language l = langs.get(outLang);
		l.editTranslation(in.indexOf(word), replacement);
		save();
	}
	
	public void create(String langName, String words) {
		Language lang = new Language();
		for(String word : words.split("\n")) {
			lang.add(word);
		}
		String next = langs.keySet().iterator().next();
		int wordsToAdd = langs.get(next).size() - lang.size();
		for(int i = 0; i < wordsToAdd; i++) {
			lang.add("?");
		}
		langs.put(langName, lang);
		save();
	}
	
	public String[] getLanguageNames() {
		String[] langNames = new String[langs.size()];
		int i = 0;
		for(String langName : langs.keySet()) {
			langNames[i] = langName;
			i++;
		}
		return langNames;
	}
	
	// getting input from a file
	private void load() {
		try {
			Scanner in = new Scanner(this.getClass().getResourceAsStream(fileName));
			// information is tab delimited
			for(String langName : in.nextLine().split("\t")) {
				// putting the information into a map
				System.out.println(langName + " is a lang");
				langs.put(langName, new Language());
			}
			while(in.hasNextLine()) {
				// iterator goes through all lang names in Map
				Iterator<String> i = langs.keySet().iterator();
				for(String word : in.nextLine().split("\t")) {
					// looping through languages and adding the word
					langs.get(i.next()).add(word);
				}
			}
			in.close();
		} catch (Exception e) { // file is always there
			e.printStackTrace();
		}
	}
	
	// writing back to the file
	private void save() {
		try {
			PrintWriter pw = new PrintWriter(new File(fileName));
			for(String langName : langs.keySet()) {
				pw.print(langName + "\t");
			}
			pw.println();
			// simply need to get a language in the Map
			String next = langs.keySet().iterator().next();
			for(int i = 0; i < langs.get(next).size(); i++) {
				// writing a word in all languages
				for(String langName : langs.keySet()) {
					pw.print(langs.get(langName).get(i) + "\t");
				}
				pw.println();
			}
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
