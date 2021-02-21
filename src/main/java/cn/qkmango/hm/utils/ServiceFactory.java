package cn.qkmango.hm.utils;

public class ServiceFactory {
	
	public static Object getService(Object service){
		
		return new cn.qkmango.hm.utils.TransactionInvocationHandler(service).getProxy();
		
	}
	
}
