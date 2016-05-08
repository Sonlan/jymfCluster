package org.jymf.utils;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

public class FileUtils {

	/**
	 * 自动建立文件夹
	 * 
	 * @param filePath 立文件夹路径
	 */
	public static void mkdir(String filePath) {
		File _filePath = new File(filePath);
		if (!_filePath.exists()) {
			_filePath.mkdirs();
		}
	}

	/**
	 * 得到文件后缀(如sample.jpg->.jpg)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return "";
		}
		int pos = fileName.lastIndexOf(".");
		if (pos < 0) {
			return fileName.substring(fileName.length() - 3).toLowerCase();
		} else {
			return fileName.substring(pos).toLowerCase();
		}
	}

	/**
	 * 得到文件名称无后缀(如sample.jpg->sample)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileNameNoSuffix(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return "";
		}
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(0, pos);
	}
	/**
	 * 删除指定前缀的文件
	 * @param path    文件路径
	 * @param prefix  文件的前缀
	 * @return 
	 *        True    文件有删除
	 *       False    文件无删除
	 */
	public static boolean fileDelete(String path,String prefix){
		File dir = new File(path);
		
		if (!dir.exists()) {
			return false;
		}
		File file[] = dir.listFiles();
		for (int i = 0; i < file.length; i++) {
			if(file[i].getName().startsWith(prefix)){
				file[i].delete();
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 删除指定文件
	 * @param fileName  文件名称带路径
	 */
	public static void fileDelete(String fileName){
	    File file = new File(fileName);
	    if(!file.exists()){
	    	return;
	    }
		
	    file.delete();
	}
	
	/**
	 * 根据文件名在某个文件夹下获取该文件的格式后缀 etc: ".txt"  ".jpg"
	 * @author wfj @2015.4.22
	 * */
	public static String getSuffix(String path,String name){
		String suffix="";
		File dir=new File(path);
		if(null != dir.list()){
			for(String file_name : dir.list()){
				if(file_name.substring(0, file_name.lastIndexOf(".")).equals(name)){
					suffix=file_name.substring(file_name.lastIndexOf("."));
				}
			}
		}
		return suffix;
	}	
	
}
