package tac.kbp.kb.query;

public class GoldenStandardQuery {
	
	String query_id;
	String id;
    String name; 
    String entity_kb_id;
    String entity_type;
    
    public GoldenStandardQuery(String query_id_, String id_, String name_, String entity_kb_id_, String entity_type_){
    	
    	this.query_id = query_id_;
    	this.id = id_;
    	this.name = name_;
    	this.entity_kb_id = entity_kb_id_;
    	this.entity_type = entity_type_;    	
    }
    
}
