
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itko.lisa.ext.enumeration.Operation;
import com.itko.lisa.ext.handlers.RequestHandler;
import com.itko.lisa.ext.handlers.ResponseHandler;
import com.itko.lisa.ext.vsi.RequestContent;
import com.itko.lisa.ext.vsi.ResponseContent;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.model.ServiceImage;
import com.itko.lisa.vse.stateful.model.Transaction;
import com.itko.lisa.vse.stateful.model.TransactionNode;
import com.itko.lisa.vse.stateful.model.xml.ServiceImageContext;
import com.itko.util.ParameterList;


public class ParsingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String vsiFilePath = "C://sltest//branches//b1303_P1_myATT_Validation_Server2_Merged//IST_Stubbing_1303//VServices//MERGED//WEBSERVICES//Images//";
		String vsiFileName = "CSI_Merged.vsi";
		String vsiFile = vsiFilePath+vsiFileName;
		ServiceImage si = ServiceImageContext.readServiceImage(vsiFile);
		List<TransactionNode> stTxs = si.getStatelessTransactions();
		for (TransactionNode tnode : stTxs) {
			List<Transaction> txns = tnode.getSpecifics();
			Request metaReq = tnode.getRequest();
			String operation = metaReq.getOperation();
//			List<RequestContent> reqFilters = new ArrayList<RequestContent>();
//			reqFilters.add(addToRequestContent());
			List<ResponseContent> resFilters = new ArrayList<ResponseContent>();
			resFilters.add(updateToResponseContent());
			if (validateTxn(operation,metaReq)) {
//				RequestHandler.handleMetaRequest(reqFilters, tnode);
				ResponseHandler.handleMetaResponse(resFilters, tnode);
				for(Transaction txn : txns){
//					RequestHandler.handlleRequest(reqFilters, txn);
					ResponseHandler.handleResponse(resFilters, txn);
				}
			}
		}
		ServiceImageContext.writeXML(vsiFile, si);
	}
	
//	private static boolean validateTxn(String operation,Request request) throws Exception {
//		ParameterList params = request.getArguments();
//		if ("InquireUnifiedCustomerLoginProfileRequest".equalsIgnoreCase(operation)) {
//			if(params.size() == 9){
//				return true;
//			}
//		}
//		return false;
//	}
	
	private static boolean validateTxn(String operation,Request request) throws Exception {
		ParameterList params = request.getArguments();
		if ("InquireUnifiedCustomerLoginProfileRequest".equalsIgnoreCase(operation)) {
			if(params.size() == 10){
				return true;
			}
		}
		return false;
	}
	
	
//	private static boolean validateTxn(String operation,Request request) throws Exception {
//		ParameterList params = request.getArguments();
//		if ("InquireUnifiedCustomerLoginProfileRequest".equalsIgnoreCase(operation)) {
//			if(params.size() == 9){
//				return true;
//			}
//		}
//		return false;
//	}
	
	
	/**
	 *  for Static Add
	 * @return
	 * @throws Exception
	 */
	private static RequestContent addToRequestContent() throws Exception {
		RequestContent requestContent = new RequestContent();
		requestContent.setOpr(Operation.INSERT);
		requestContent.setArgname("dipen");
		requestContent.setArgvalue("dipen");
		return requestContent;
	}

	
	private static RequestContent removeFromRequestContent() throws Exception {
		RequestContent requestContent = new RequestContent();
		requestContent.setOpr(Operation.DELETE);
		requestContent.setArgname("dipen");
		return requestContent;
	}
	
	private static RequestContent addContextualAttributeToRequestContent() throws Exception {
		RequestContent requestContent = new RequestContent();
		requestContent.setOpr(Operation.INSERT);
		Map<String,String> ctxtMap = new HashMap<String,String>();
		ctxtMap.put("arg6_ACHECKDATA_AMETHOD:ACH::arg6_ISBUSINESSBANKACCOUNT:false", "dipen:dipen");
		requestContent.setCtxtMap(ctxtMap);
		List<String> ctxtKeys = new ArrayList<String>();
		ctxtKeys.add("arg6_ACHECKDATA_AMETHOD");
		requestContent.setCtxtKeys(ctxtKeys);
		return requestContent;
	}
	
	private static ResponseContent addToResponseContent(){
//		String xPath = "/lisa.vse.java.response/lisa.vse.java.response.result/com.sbc.eia.idl.pam.RetrievePaymentProfileReturn/aPaymentProfiles/com.sbc.eia.idl.pam__types.PaymentProfile/aPaymentProfileMethods/com.sbc.eia.idl.pam__types.PaymentProfileMethod/______aCheck";
		String xPath = "//*[local-name()='com.sbc.eia.idl.pam.RetrievePaymentProfileReturn']/*[local-name()='aPaymentProfiles']/*[local-name()='com.sbc.eia.idl.pam__types.PaymentProfile']/*[local-name()='aPaymentProfileMethods']/*[local-name()='com.sbc.eia.idl.pam__types.PaymentProfileMethod']/*[local-name()='______aCheck']";
		String xPathValue = "<dipen>bhatt</dipen>";
		ResponseContent responseContent = new ResponseContent();
		responseContent.setOpr(Operation.INSERT);
		responseContent.setXpath(xPath);
		responseContent.setXpathvalue(xPathValue);
		return responseContent;
	}
	
	private static ResponseContent removeToResponseContent(){
//		String xPath = "/lisa.vse.java.response/lisa.vse.java.response.result/com.sbc.eia.idl.pam.RetrievePaymentProfileReturn/aPaymentProfiles/com.sbc.eia.idl.pam__types.PaymentProfile/aPaymentProfileMethods/com.sbc.eia.idl.pam__types.PaymentProfileMethod/dhichkyav";
		String xPath = "//*[local-name()='CustomerLoginProfile']/*[local-name()='Accounts']/*[local-name()='Account']/*[local-name()='WirelessAccount']/*[local-name()='AccountType']/*[local-name()='accountSubType']";
//		String xPathValue = "<dipen>bhatt</dipen>";
		ResponseContent responseContent = new ResponseContent();
		responseContent.setOpr(Operation.DELETE);
		responseContent.setXpath(xPath);
//		responseContent.setXpathvalue(xPathValue);
		return responseContent;
	}

	private static ResponseContent addContextualAttributeToResponseContent(){
		String xPath = "//*[local-name()='com.sbc.eia.idl.pam.RetrievePaymentProfileReturn']/*[local-name()='aPaymentProfiles']/*[local-name()='com.sbc.eia.idl.pam__types.PaymentProfile']/*[local-name()='aPaymentProfileMethods']/*[local-name()='com.sbc.eia.idl.pam__types.PaymentProfileMethod']/*[local-name()='______aCheck']";
		String xPathValue = "<dipen>bhatt</dipen>";
		ResponseContent responseContent = new ResponseContent();
		responseContent.setOpr(Operation.INSERT);
		Map<String,String> ctxtMap = new HashMap<String,String>();
		ctxtMap.put("arg1_COM_SBC_EIA_IDL_PAM__TYPES_PAYMENTPROFILEITEM_APROFILEUSER:552479125::arg1_COM_SBC_EIA_IDL_PAM__TYPES_PAYMENTPROFILEITEM_ASOURCEUSER:MyWorld", xPath+":"+xPathValue);
		responseContent.setCtxtMap(ctxtMap);
		List<String> ctxtKeys = new ArrayList<String>();
		ctxtKeys.add("arg1_COM_SBC_EIA_IDL_PAM__TYPES_PAYMENTPROFILEITEM_APROFILEUSER");
		ctxtKeys.add("arg1_COM_SBC_EIA_IDL_PAM__TYPES_PAYMENTPROFILEITEM_ASOURCEUSER");
		responseContent.setCtxtKeys(ctxtKeys);
		return responseContent;
	}
	
	private static ResponseContent addContextualAttributeToResponseContentForCSI(){
		String xPath = "//*[local-name()='CustomerLoginProfile']/*[local-name()='Accounts']/*[local-name()='Account']/*[local-name()='WirelessAccount']/*[local-name()='billingAccountNumber']";
		String xPathValue = "<crossMarketIndicator>true</crossMarketIndicator>";
		ResponseContent responseContent = new ResponseContent();
		responseContent.setOpr(Operation.INSERT);
		responseContent.setXpath(xPath);
		responseContent.setXpathvalue(xPathValue);
		return responseContent;
	}
	
	private static ResponseContent updateToResponseContent(){
//		String xPath = "/lisa.vse.java.response/lisa.vse.java.response.result/com.sbc.eia.idl.pam.RetrievePaymentProfileReturn/aPaymentProfiles/com.sbc.eia.idl.pam__types.PaymentProfile/aPaymentProfileMethods/com.sbc.eia.idl.pam__types.PaymentProfileMethod/______aCheck";
		String xPath = "//*[local-name()='CustomerLoginProfile']/*[local-name()='Accounts']/*[local-name()='Account']/*[local-name()='WirelessAccount']/*[local-name()='billingFullName']";
		String xPathValue = "<billingFullName>bhatt</billingFullName>";
		ResponseContent responseContent = new ResponseContent();
		responseContent.setOpr(Operation.UPDATE);
		responseContent.setXpath(xPath);
		responseContent.setXpathvalue(xPathValue);
		return responseContent;
	}	
	
}
