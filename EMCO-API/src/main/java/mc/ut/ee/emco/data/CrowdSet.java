package mc.ut.ee.emco.data;

import java.util.List;

public class CrowdSet {
	private String linguisticVariable;
	private List<CrowdTerm> linguisticTerms; //TODO-this should be <String, functionType, functionValues>
	private String setDescription;
	
	public String getLinguisticVariable(){
		return linguisticVariable;
	}
	
	public List<CrowdTerm> getLinguisticTerms(){
		return linguisticTerms;
	}
	
	public String getSetDescription(){
		return setDescription;
	}
	
}
