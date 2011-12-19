package tac.kbp.ranking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import tac.kbp.bin.Definitions;
import tac.kbp.queries.GoldQuery;
import tac.kbp.queries.KBPQuery;
import tac.kbp.queries.KBPQueryComparator;
import tac.kbp.queries.candidates.Candidate;
import tac.kbp.queries.candidates.CandidateComparator;

public class SVMRank {
	
	/*
	# query 1
	3 qid:1 1:1 2:1 3:0 4:0.2 5:0
	2 qid:1 1:0 2:0 3:1 4:0.1 5:1
	1 qid:1 1:0 2:1 3:0 4:0.4 5:0
	1 qid:1 1:0 2:0 3:1 4:0.3 5:0
	*/
	
	public void svmRankFormat(List<KBPQuery> queries, HashMap<String, GoldQuery> queries_answers, String outputfile) throws IOException {
		
		FileWriter fstream = new FileWriter(outputfile);
		BufferedWriter out = new BufferedWriter(fstream);

		//to avoid: "ERROR: Query ID's in data file have to be in increasing order" from SVMRank
		//sort queries according to id  in increasing order
		Collections.sort(queries, new KBPQueryComparator());
		
		for (KBPQuery q : queries) {
			
			out.write("#" + q.query_id + " " + q.gold_answer + "\n");
			
			for (Candidate c : q.candidates) {
				
				double[] vector = c.features.featuresVector();
				
				if (queries_answers.get(q.query_id).answer.equalsIgnoreCase(c.entity.id)) {
					out.write("1"+" ");
				}
				
				else out.write("0"+" ");
				
				String[] query_parts = q.query_id.split("EL");			
				out.write("qid:"+Integer.parseInt(query_parts[1])+" ");
				
				for (int i = 0; i < vector.length; i++) {
					out.write((i+1)+":"+vector[i]+" ");
				}
				out.write("#" + c.entity.id);
				out.write("\n");
			}
		}
		out.close();
	}
}
