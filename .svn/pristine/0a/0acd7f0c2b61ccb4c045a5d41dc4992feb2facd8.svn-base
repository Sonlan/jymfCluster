package org.jymf.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * csv文件操作通用类
 * @author wfj 2015.5.6 
 */
public class CSVUtils {

	/**
	 * 文件生成
	 * @param filePath=文件位置
	 * 		  dataList=导出数据	 
	 */
	public static boolean createCSV(String filePath,List<List<String>> dataList){
		boolean isSuccess=false;
		
		 FileOutputStream out=null;
	     OutputStreamWriter osw=null;
	     BufferedWriter bw=null;
	     try {
	            out = new FileOutputStream(new File(filePath));
	            osw = new OutputStreamWriter(out,"GBK");
	            bw =new BufferedWriter(osw);
	            if(dataList!=null && !dataList.isEmpty()){
	                for(List<String> list : dataList){
	                	for(String data : list){
	                		bw.append(data).append(",");
	                	}
	                	bw.append("\r");
	                }
	            }
	            isSuccess=true;
	        } catch (Exception e) {
	        	isSuccess=false;
	        }finally{
	            if(bw!=null){
	                try {
	                    bw.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
	            if(osw!=null){
	                try {
	                    osw.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
	            if(out!=null){
	                try {
	                    out.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
	        }
		
		return isSuccess;
	}
}
