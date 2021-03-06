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

public class Tester {
	private static IDictionary dict = null;

	public static void main(String[] args) throws IOException {
		List<String> listaModifiers = new ArrayList<String>();
		listaModifiers.add("Question");
		
		
		//Instanciar Dicionario
		IDictionary dict = dicitionaryFactory();
		
		// get the synset
		for(String termo : listaModifiers){
			System.out.println("== Termo buscado: "+termo);
			IIndexWord idxWord = dict.getIndexWord(termo, POS.NOUN); 
			for(IWordID wordID : idxWord.getWordIDs()){
				IWord word = dict.getWord(wordID);
				System.out.println(word.getSynset() + " --> "+word.getSynset().getLexicalFile());
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
