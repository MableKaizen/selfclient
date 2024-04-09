package testsdk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zoho.api.authenticator.OAuthToken;
import com.zoho.api.authenticator.Token;
import com.zoho.api.authenticator.store.FileStore;
import com.zoho.api.authenticator.store.TokenStore;
import com.zoho.api.logger.Logger;
import com.zoho.api.logger.Logger.Levels;
import com.zoho.crm.api.HeaderMap;
import com.zoho.crm.api.Initializer;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.RequestProxy;
import com.zoho.crm.api.SDKConfig;
import com.zoho.crm.api.dc.DataCenter.Environment;
import com.zoho.crm.api.record.RecordOperations;
import com.zoho.crm.api.record.ResponseHandler;
import com.zoho.crm.api.record.ResponseWrapper;
import com.zoho.crm.api.record.RecordOperations.GetRecordParam;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.dc.USDataCenter;



import com.zoho.crm.api.UserSignature;
import com.zoho.crm.api.dc.DataCenter;
import com.zoho.crm.api.dc.USDataCenter;
import com.zoho.crm.api.exception.SDKException;

import com.zoho.crm.api.record.RecordOperations;
import com.zoho.crm.api.record.RecordOperations.GetRecordParam;
import com.zoho.crm.api.record.ResponseHandler;
import com.zoho.crm.api.record.ResponseWrapper;
import com.zoho.crm.api.util.APIResponse;


public class Initialize
{
	public static void main(String[] args) throws Exception
	{
		initialize();
	}

	public static void initialize() throws Exception
	{
		Logger logger = new Logger.Builder().level(Levels.INFO).filePath("/Users/mable-16419/Documents/SDK/java_sdk_log.log").build();
		Environment environment = USDataCenter.PRODUCTION;
		Token token = new OAuthToken.Builder().clientID("1000.A205SW4EJL7TID9YCBN51EGALDU34D").clientSecret("8fabe9642ca15d39072c42a16fd10aa0007daf1734").grantToken("1000.976e1916af78c5a1331162cb554d06d3.778462d52a9fdadaa8f8990a00118bab").build();
		TokenStore tokenstore = new FileStore("/Users/mable-16419/Documents/SDK/java_sdk_tokens.log");
		SDKConfig sdkConfig = new SDKConfig.Builder().autoRefreshFields(false).pickListValidation(true).build();
		String resourcePath = "/Users/mable-16419/Documents/SDK";
//		RequestProxy requestProxy = new RequestProxy.Builder().host("host").port(0).user("userName").password("password").userDomain("userDomain").build();
		new Initializer.Builder().environment(environment).token(token).store(tokenstore).SDKConfig(sdkConfig).resourcePath(resourcePath).logger(logger).initialize();
	
		
		

  		RecordOperations recordOperations = new RecordOperations("Leads");
		ParameterMap paramInstance = new ParameterMap();
		
		List<String> fieldNames = new ArrayList<String>(Arrays.asList("Last_Name","id"));
		paramInstance.add(GetRecordParam.FIELDS, String.join(",", fieldNames));

		HeaderMap headerInstance = new HeaderMap();
		
	
		APIResponse<ResponseHandler> response = recordOperations.getRecords(paramInstance, headerInstance);
		ResponseHandler responseHandler = response.getObject();
		
		if (responseHandler instanceof ResponseWrapper)
		{
			ResponseWrapper responseWrapper = (ResponseWrapper) responseHandler;
			List<com.zoho.crm.api.record.Record> records = responseWrapper.getData();
			
			for(com.zoho.crm.api.record.Record record : records)
			{
				System.out.println("Lead ID :"+ record.getId());
			}
			
		}
	}
}