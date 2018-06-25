package com.att.dbvalidation.properties;


import static com.att.dbvalidation.properties.DataValidationEnum.*;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class DBConnectionProperties {
	private String releaseArray[];
	public DBConnectionProperties() throws IOException{
		releaseArray=new String[2];
		releaseArray[0] = RELEASE_CURRENT.getValue();
		releaseArray[1]=RELEASE_FUTURE.getValue();
	}

	
//Reading CCR Details from sheet
	public String[] CCR_DB(String rel) throws IOException {
		String[] CCR_Details=new String[3];
		if (rel.equals(releaseArray[0])) {
			CCR_Details[0] = CCR_CURRENT_DRIVER.getValue();
			CCR_Details[1] = CCR_CURRENT_USERNAME.getValue();
			CCR_Details[2] = CCR_CURRENT_PASSWORD.getValue();	
		}
		else if(rel.equals(releaseArray[1])) {
			CCR_Details[0] = CCR_FUTURE_DRIVER.getValue();
			CCR_Details[1] = CCR_FUTURE_USERNAME.getValue();
			CCR_Details[2] = CCR_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("CCR DB Details: "+CCR_Details[0]+" - "+CCR_Details[1]+" - "+CCR_Details[2]);
		return CCR_Details;
	}
	//Reading MPS Details from properties file	
	public String[] MPS_DB(String rel) {
		String MPS_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			MPS_Details[0] = MPS_CURRENT_DRIVER.getValue();
			MPS_Details[1] = MPS_CURRENT_USERNAME.getValue();
			MPS_Details[2] = MPS_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			MPS_Details[0] = MPS_FUTURE_DRIVER.getValue();
			MPS_Details[1] = MPS_FUTURE_USERNAME.getValue();
			MPS_Details[2] = MPS_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("MPS DB Details: "+MPS_Details[0]+" - "+MPS_Details[1]+" - "+MPS_Details[2]);
		return MPS_Details;
	}	
	
//Reading Enb Z1 Details from sheet
	public String[] EnbZ1_DB(String rel) throws IOException {
		String EnbZ1_Details[]=new String[3];
		if (rel.equals(releaseArray[0])) {
			EnbZ1_Details[0] = ENABLER_ZONE1_CURRENT_DRIVER.getValue();
			EnbZ1_Details[1] = ENABLER_ZONE1_CURRENT_USERNAME.getValue();
			EnbZ1_Details[2] = ENABLER_ZONE1_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			EnbZ1_Details[0] = ENABLER_ZONE1_FUTURE_DRIVER.getValue();
			EnbZ1_Details[1] = ENABLER_ZONE1_FUTURE_USERNAME.getValue();
			EnbZ1_Details[2] = ENABLER_ZONE1_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("Enabler Z1 DB Details: "+EnbZ1_Details[0]+" - "+EnbZ1_Details[1]+" - "+EnbZ1_Details[2]);
		return EnbZ1_Details;
	}
	
//Reading Enb Z2 Details from sheet	
	public String[] EnbZ2_DB(String rel) throws IOException {
		String EnbZ2_Details[]=new String[3];
		if (rel.equals(releaseArray[0])) {
			EnbZ2_Details[0] = ENABLER_ZONE2_CURRENT_DRIVER.getValue();
			EnbZ2_Details[1] = ENABLER_ZONE2_CURRENT_USERNAME.getValue();
			EnbZ2_Details[2] = ENABLER_ZONE2_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			EnbZ2_Details[0] = ENABLER_ZONE2_FUTURE_DRIVER.getValue();
			EnbZ2_Details[1] = ENABLER_ZONE2_FUTURE_USERNAME.getValue();
			EnbZ2_Details[2] = ENABLER_ZONE2_FUTURE_PASSWORD.getValue();
		}
			//LoggerWrapper.logger.info("Enabler Z2 DB Details: "+EnbZ2_Details[0]+" - "+EnbZ2_Details[1]+" - "+EnbZ2_Details[2]);
			return EnbZ2_Details;
	}
	
//Reading Enb Z3 Details from sheet
	public String[] EnbZ3_DB(String rel) throws IOException {
		String EnbZ3_Details[]=new String[3];
		if (rel.equals(releaseArray[0])) {
			EnbZ3_Details[0] = ENABLER_ZONE3_CURRENT_DRIVER.getValue();
			EnbZ3_Details[1] = ENABLER_ZONE3_CURRENT_USERNAME.getValue();
			EnbZ3_Details[2] = ENABLER_ZONE3_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			EnbZ3_Details[0] = ENABLER_ZONE3_FUTURE_DRIVER.getValue();
			EnbZ3_Details[1] = ENABLER_ZONE3_FUTURE_USERNAME.getValue();
			EnbZ3_Details[2] = ENABLER_ZONE3_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("Enabler Z3 DB Details: "+EnbZ3_Details[0]+" - "+EnbZ3_Details[1]+" - "+EnbZ3_Details[2]);
		return EnbZ3_Details;
	}
	
//Reading OMS Seg1 Details from sheet
	public String[]  OMS1_DB(String rel) throws IOException {
		String OMS1_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			OMS1_Details[0] = OMS_SEG1_CURRENT_DRIVER.getValue();
			OMS1_Details[1] = OMS_SEG1_CURRENT_USERNAME.getValue();
			OMS1_Details[2] = OMS_SEG1_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			OMS1_Details[0] = OMS_SEG1_FUTURE_DRIVER.getValue();
			OMS1_Details[1] = OMS_SEG1_FUTURE_USERNAME.getValue();
			OMS1_Details[2] = OMS_SEG1_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("OMS1 DB Details: "+OMS1_Details[0]+" - "+OMS1_Details[1]+" - "+OMS1_Details[2]);
		return OMS1_Details;
	}
//Reading OMS Seg2 Details from sheet
	public String[] OMS2_DB(String rel) throws IOException {
		String OMS2_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			OMS2_Details[0] = OMS_SEG2_CURRENT_DRIVER.getValue();
			OMS2_Details[1] = OMS_SEG2_CURRENT_USERNAME.getValue();
			OMS2_Details[2] = OMS_SEG2_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			OMS2_Details[0] = OMS_SEG2_FUTURE_DRIVER.getValue();
			OMS2_Details[1] = OMS_SEG2_FUTURE_USERNAME.getValue();
			OMS2_Details[2] = OMS_SEG2_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("OMS2 DB Details: "+OMS2_Details[0]+" - "+OMS2_Details[1]+" - "+OMS2_Details[2]);
		return OMS2_Details;
	}	
	
//Reading OMS Seg3 Details from sheet
	public String[] OMS3_DB(String rel) throws IOException {
		String OMS3_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			OMS3_Details[0] = OMS_SEG3_CURRENT_DRIVER.getValue();
			OMS3_Details[1] = OMS_SEG3_CURRENT_USERNAME.getValue();
			OMS3_Details[2] = OMS_SEG3_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			OMS3_Details[0] = OMS_SEG3_FUTURE_DRIVER.getValue();
			OMS3_Details[1] = OMS_SEG3_FUTURE_USERNAME.getValue();
			OMS3_Details[2] = OMS_SEG3_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("OMS3 DB Details: "+OMS3_Details[0]+" - "+OMS3_Details[1]+" - "+OMS3_Details[2]);
		return OMS3_Details;
	}
	
	
//Reading Batch Details from sheet
	public String[] Batch_DB(String rel) throws IOException {
		String Batch_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			Batch_Details[0] = BATCH_CURRENT_DRIVER.getValue();
			Batch_Details[1] = BATCH_CURRENT_USERNAME.getValue();
			Batch_Details[2] = BATCH_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			Batch_Details[0] = BATCH_FUTURE_DRIVER.getValue();
			Batch_Details[1] = BATCH_FUTURE_USERNAME.getValue();
			Batch_Details[2] = BATCH_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("Batch DB Details: "+Batch_Details[0]+" - "+Batch_Details[1]+" - "+Batch_Details[2]);
		return Batch_Details;
	}
	
//Reading BDS Details from sheet	 
	public String[] BDS_DB(String rel) throws IOException {
		String BDS_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			BDS_Details[0] = BDS_CURRENT_DRIVER.getValue();
			BDS_Details[1] = BDS_CURRENT_USERNAME.getValue();
			BDS_Details[2] = BDS_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			BDS_Details[0] = BDS_CURRENT_DRIVER.getValue();
			BDS_Details[1] = BDS_CURRENT_USERNAME.getValue();
			BDS_Details[2] = BDS_CURRENT_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("BDS DB Details: "+BDS_Details[0]+" - "+BDS_Details[1]+" - "+BDS_Details[2]);
		return  BDS_Details;
	}
	
//Reading ENB Wireless Details from sheet
	public String[] ENB_DB_DLS(String rel) throws IOException {
		String ENB_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			ENB_Details[0] = ENABLER_DLS_CURRENT_DRIVER.getValue();
			ENB_Details[1] = ENABLER_DLS_CURRENT_USERNAME.getValue();
			ENB_Details[2] = ENABLER_DLS_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			ENB_Details[0] = ENABLER_DLS_FUTURE_DRIVER.getValue();
			ENB_Details[1] = ENABLER_DLS_FUTURE_USERNAME.getValue();
			ENB_Details[2] = ENABLER_DLS_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("ENB DLS DB Details: "+ENB_Details[0]+" - "+ENB_Details[1]+" - "+ENB_Details[2]);
		return ENB_Details;
	}
	public String[] ENB_DB_GAC(String rel) throws IOException {
		String ENB_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			ENB_Details[0] = ENABLER_GAC_CURRENT_DRIVER.getValue();
			ENB_Details[1] = ENABLER_GAC_CURRENT_USERNAME.getValue();
			ENB_Details[2] = ENABLER_GAC_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			ENB_Details[0] = ENABLER_GAC_FUTURE_DRIVER.getValue();
			ENB_Details[1] = ENABLER_GAC_FUTURE_USERNAME.getValue();
			ENB_Details[2] = ENABLER_GAC_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("ENB GAC DB Details: "+ENB_Details[0]+" - "+ENB_Details[1]+" - "+ENB_Details[2]);
		return ENB_Details;
	}
	public String[] ENB_DB_MWR(String rel) throws IOException {
		String ENB_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			ENB_Details[0] = ENABLER_MWR_CURRENT_DRIVER.getValue();
			ENB_Details[1] = ENABLER_MWR_CURRENT_USERNAME.getValue();
			ENB_Details[2] = ENABLER_MWR_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			ENB_Details[0] = ENABLER_MWR_FUTURE_DRIVER.getValue();
			ENB_Details[1] = ENABLER_MWR_FUTURE_USERNAME.getValue();
			ENB_Details[2] = ENABLER_MWR_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("ENB MWR DB Details: "+ENB_Details[0]+" - "+ENB_Details[1]+" - "+ENB_Details[2]);
		return ENB_Details;
	}
	public String[] ENB_DB_NBI(String rel) throws IOException {
		String ENB_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			ENB_Details[0] = ENABLER_NBI_CURRENT_DRIVER.getValue();
			ENB_Details[1] = ENABLER_NBI_CURRENT_USERNAME.getValue();
			ENB_Details[2] = ENABLER_NBI_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			ENB_Details[0] = ENABLER_NBI_FUTURE_DRIVER.getValue();
			ENB_Details[1] = ENABLER_NBI_FUTURE_USERNAME.getValue();
			ENB_Details[2] = ENABLER_NBI_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("ENB NBI DB Details: "+ENB_Details[0]+" - "+ENB_Details[1]+" - "+ENB_Details[2]);
		return ENB_Details;
	}
	public String[] ENB_DB_PAC(String rel) throws IOException {
		String ENB_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			ENB_Details[0] = ENABLER_PAC_CURRENT_DRIVER.getValue();
			ENB_Details[1] = ENABLER_PAC_CURRENT_USERNAME.getValue();
			ENB_Details[2] = ENABLER_PAC_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			ENB_Details[0] = ENABLER_PAC_FUTURE_DRIVER.getValue();
			ENB_Details[1] = ENABLER_PAC_FUTURE_USERNAME.getValue();
			ENB_Details[2] = ENABLER_PAC_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("ENB PAC DB Details: "+ENB_Details[0]+" - "+ENB_Details[1]+" - "+ENB_Details[2]);
		return ENB_Details;
	}
//Reading TLG Details from sheet
	public String[] TLG_DB_DLS(String rel) throws IOException {
		String TLG_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			TLG_Details[0] = TLG_DLS_CURRENT_DRIVER.getValue();
			TLG_Details[1] = TLG_DLS_CURRENT_USERNAME.getValue();
			TLG_Details[2] = TLG_DLS_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			TLG_Details[0] = TLG_DLS_FUTURE_DRIVER.getValue();
			TLG_Details[1] = TLG_DLS_FUTURE_USERNAME.getValue();
			TLG_Details[2] = TLG_DLS_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("TLG DLS DB Details: "+TLGDLS_Details[0]+" - "+TLGDLS_Details[1]+" - "+TLGDLS_Details[2]);
		return TLG_Details;
	}
	public String[] TLG_DB_PAC(String rel) throws IOException {
		String TLG_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			TLG_Details[0] = TLG_PAC_CURRENT_DRIVER.getValue();
			TLG_Details[1] = TLG_PAC_CURRENT_USERNAME.getValue();
			TLG_Details[2] = TLG_PAC_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			TLG_Details[0] = TLG_PAC_FUTURE_DRIVER.getValue();
			TLG_Details[1] = TLG_PAC_FUTURE_USERNAME.getValue();
			TLG_Details[2] = TLG_PAC_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("TLG PAC DB Details: "+TLGPAC_Details[0]+" - "+TLGPAC_Details[1]+" - "+TLGPAC_Details[2]);
		return TLG_Details;
	}
	public String[] TLG_DB_SAN(String rel) throws IOException {
		String TLG_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			TLG_Details[0] = TLG_SAN_CURRENT_DRIVER.getValue();
			TLG_Details[1] = TLG_SAN_CURRENT_USERNAME.getValue();
			TLG_Details[2] = TLG_SAN_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			TLG_Details[0] = TLG_SAN_FUTURE_DRIVER.getValue();
			TLG_Details[1] = TLG_SAN_FUTURE_USERNAME.getValue();
			TLG_Details[2] = TLG_SAN_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("TLG SAN DB Details: "+TLGDLS_Details[0]+" - "+TLGDLS_Details[1]+" - "+TLGDLS_Details[2]);
		return TLG_Details;
	}
	public String[] TLG_DB_MWR(String rel) throws IOException {
		String TLG_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			TLG_Details[0] = TLG_MWR_CURRENT_DRIVER.getValue();
			TLG_Details[1] = TLG_MWR_CURRENT_USERNAME.getValue();
			TLG_Details[2] = TLG_MWR_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			TLG_Details[0] = TLG_MWR_FUTURE_DRIVER.getValue();
			TLG_Details[1] = TLG_MWR_FUTURE_USERNAME.getValue();
			TLG_Details[2] = TLG_MWR_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("TLG MWR DB Details: "+TLGPAC_Details[0]+" - "+TLGPAC_Details[1]+" - "+TLGPAC_Details[2]);
		return TLG_Details;
	}
	public String[] TLG_DB_GAC(String rel) throws IOException {
		String TLG_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			TLG_Details[0] = TLG_GAC_CURRENT_DRIVER.getValue();
			TLG_Details[1] = TLG_GAC_CURRENT_USERNAME.getValue();
			TLG_Details[2] = TLG_GAC_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			TLG_Details[0] = TLG_GAC_FUTURE_DRIVER.getValue();
			TLG_Details[1] = TLG_GAC_FUTURE_USERNAME.getValue();
			TLG_Details[2] = TLG_GAC_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("TLG GAC DB Details: "+TLGDLS_Details[0]+" - "+TLGDLS_Details[1]+" - "+TLGDLS_Details[2]);
		return TLG_Details;
	}
	public String[] TLG_DB_GLR(String rel) throws IOException {
		String TLG_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			TLG_Details[0] = TLG_GLR_CURRENT_DRIVER.getValue();
			TLG_Details[1] = TLG_GLR_CURRENT_USERNAME.getValue();
			TLG_Details[2] = TLG_GLR_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			TLG_Details[0] = TLG_GLR_FUTURE_DRIVER.getValue();
			TLG_Details[1] = TLG_GLR_FUTURE_USERNAME.getValue();
			TLG_Details[2] = TLG_GLR_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("TLG GLR DB Details: "+TLGPAC_Details[0]+" - "+TLGPAC_Details[1]+" - "+TLGPAC_Details[2]);
		return TLG_Details;
	}
	public String[] TLG_DB_NBI(String rel) throws IOException {
		String TLG_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			TLG_Details[0] = TLG_NBI_CURRENT_DRIVER.getValue();
			TLG_Details[1] = TLG_NBI_CURRENT_USERNAME.getValue();
			TLG_Details[2] = TLG_NBI_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			TLG_Details[0] = TLG_DLS_FUTURE_DRIVER.getValue();
			TLG_Details[1] = TLG_DLS_FUTURE_USERNAME.getValue();
			TLG_Details[2] = TLG_DLS_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("TLG NBI DB Details: "+TLGNBI_Details[0]+" - "+TLGNBI_Details[1]+" - "+TLGNBI_Details[2]);
		return TLG_Details;
	}
	public String[] TLG_DB_NWS(String rel) throws IOException {
		String TLG_Details[]=new String[3];
		if(rel.equals(releaseArray[0])) {
			TLG_Details[0] = TLG_NWS_CURRENT_DRIVER.getValue();
			TLG_Details[1] = TLG_NWS_CURRENT_USERNAME.getValue();
			TLG_Details[2] = TLG_NWS_CURRENT_PASSWORD.getValue();
		}
		else if(rel.equals(releaseArray[1])) {
			TLG_Details[0] = TLG_NWS_FUTURE_DRIVER.getValue();
			TLG_Details[1] = TLG_NWS_FUTURE_USERNAME.getValue();
			TLG_Details[2] = TLG_NWS_FUTURE_PASSWORD.getValue();
		}
		//LoggerWrapper.logger.info("TLG NWS DB Details: "+TLGNWS_Details[0]+" - "+TLGNWS_Details[1]+" - "+TLGNWS_Details[2]);
		return TLG_Details;
	}

	


}

