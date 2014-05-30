package br.uniriotec.ppgi.mapping.searcher;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;

public class ExtractSynsetID {
	private static IDictionary dict = null;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		List<String> listaModifiers = new ArrayList<String>();
		listaModifiers.add("Extraterrestrial Object");
		listaModifiers.add("Celestial Body");
		listaModifiers.add("flora");
		listaModifiers.add("Natural object");
		listaModifiers.add("Substance");
		listaModifiers.add("Relative");
		listaModifiers.add("Parent");
		listaModifiers.add("Social Group");
		listaModifiers.add("Time Period");
		listaModifiers.add("Green Goods");
		listaModifiers.add("Meat");
		listaModifiers.add("Food Product");
		
		
		//Instanciar Dicionario
		IDictionary dict = dicitionaryFactory();
		
		// get the synset
		for(String termo : listaModifiers){
			System.out.println("== Termo buscado: "+termo);
			IIndexWord idxWord = dict.getIndexWord(termo, POS.NOUN); 
			for(IWordID wordID : idxWord.getWordIDs()){
				IWord word = dict.getWord(wordID);
				System.out.println(word.getSynset());
			}
			System.out.println("----");
		}
		
		
	}
	
	
	
	
	
	
	
	public static IDictionary dicitionaryFactory() throws IOException{
		if(dict == null){
			System.out.println("Instanciando Dicionario...");
			// construct the URL to the Wordnet dictionary directory
			String wnhome = System.getenv("WNHOME");
			String path = wnhome + File.separator + "dict"; 
			URL url = new URL("file", null, path);
			// construct the dictionary object and open it
			dict = new Dictionary(url); 
			dict.open();
		}
		return dict;
	}
	


}
