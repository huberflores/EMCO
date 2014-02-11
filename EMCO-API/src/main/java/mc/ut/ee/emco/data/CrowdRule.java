package mc.ut.ee.emco.data;

public class CrowdRule {
	private String operand1;	//TODO-This should be adapted according to the number of operands that can be in a fuzzy operation
	private String operand2;
	private String operation;
	private String result;
	private String ruleDescription;

	public String getOperand1(){
		return operand1;
	}
	
	public String getOperand2(){
		return operand2;
	}
	
	public String getOperation(){
		return operation;
	}
	
	public String getResult(){
		return result;
	}
}
