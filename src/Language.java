import java.util.ArrayList;

public class Language {

	/*
	 * encapsulation of the Arraylist
	 * only certain methods can be accessed like
	 * getting and setting an index, adding an element,
	 * and finding the index of an element for example
	 */
	private ArrayList<String> words;
	
	public Language() {
		words = new ArrayList<>();
	}
	
	public String get(int index) {
		return words.get(index);
	}
	
	public void editTranslation(int index, String replacement) {
		words.set(index, replacement);
	}
	
	public void removeTranslation(int index) {
		words.remove(index);
	}
	
	public boolean contains(String word) {
		return words.contains(word.toLowerCase());
	}
	
	public int indexOf(String word) {
		return words.indexOf(word);
	}
	
	public void add(String word) {
		words.add(word);
	}
	
	public int size() {
		return words.size();
	}
	
}
