package org.terrier.terms;

import java.io.IOException;
import java.util.ArrayList;

import com.qcri.farasa.segmenter.ArabicUtils;
import com.qcri.farasa.segmenter.Farasa;


/**
*
* @author El Mahdaouy Abdelkader
*/

public class FarasaStemmer extends StemmerTermPipeline {

	Farasa segmenter ;
	public FarasaStemmer() {
		super();
		
		try {
			segmenter = new Farasa();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public FarasaStemmer(TermPipeline _next) {
		super(_next);
		try {
			segmenter = new Farasa();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String stem(String s) {
		String norm = ArabicUtils.replaceFarsiCharacters(s);
				ArrayList<String> words = segmenter.segmentLine(norm);
		String output = "";
		for (String o : words)
		{
	            String seg = segmenter.getProperSegmentation(o).replace(";", " ") + " ";
	            String tab[]=seg.split(" ");
	            for(int i = 0; i < tab.length; i++) {
	                    String tab1 = tab[i];
	                    if (!tab1.contains("+")) output += tab1+" ";
	                    
	                }
		     
		}
		return ArabicUtils.normalizeFull(output.trim());
		
	}



}
