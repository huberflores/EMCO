package mc.ut.ee.emco.core;

import java.util.logging.Logger;

import mc.ut.ee.emco.data.CrowdCharacterization;
import mc.ut.ee.emco.util.CommonUtilities;

import com.google.gson.Gson;

/**
 * @author Huber Flores
 */

public class EMCOActivator {
	
	private Logger log;
	private CrowdCharacterization data = null;
	
	
	public EMCOActivator(){
		log = Logger.getLogger(EMCOActivator.class.getName());
		
		applyDefinitions();
		startEMCO();
	}
	
	
	public void applyDefinitions(){
		Gson gson = new Gson();
		Class taskClass;
		
		try {
			taskClass = Class.forName(CommonUtilities.CLASS_PATH_ROUTINES + "EMCOManager");
			
			log.warning("static JSON definition");
			String characterizationDescriptor = CommonUtilities.JSON_PATTERN_SAMPLE;
		
			data = (CrowdCharacterization) gson.fromJson(characterizationDescriptor, taskClass);
		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	public void startEMCO(){
		
		if (data!=null){
			EMCOManager manager = (EMCOManager) data;
			manager.applyCharacterization();
		}
		
		
		
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new EMCOActivator();
	}

}
