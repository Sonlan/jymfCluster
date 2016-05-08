package org.jymf.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jymf.dfs.Client;
import com.jymf.dfs.bean.AbstractJson;
import com.jymf.dfs.bean.DeleteDownJson;
import com.jymf.dfs.bean.DownloadDownJson;
import com.jymf.dfs.bean.UploadDownJson;
import com.jymf.dfs.bean.RespContentJson;

@Service
public class FileManager {

	@Resource
	private SysConfig sysConfig;
	
    /**
     * 保存用户上传的文件
     * @param imgFiles
     * @param itemId    参考说明文档
     * @return
     */
	public boolean saveUploadFile(List<File> imgFiles, String itemId)  {
		Client client = new Client();
        client.connect();
        try {
        	// upload("JYMF",参考文档,ext,文件二进制)
        	// upload(userName,itemId,fileOrder,ext,fileContent)
        	for(int i=0;i<imgFiles.size();i++){
        		if(null == imgFiles.get(i))
        			continue;
        		
        		if(!imgFiles.get(i).exists()){
    				continue;
    			}
    			
        		String fileName = imgFiles.get(i).getName();
        		String suffix=fileName.substring(fileName.lastIndexOf(".")+1);
        		UploadDownJson  result = client.upload("JYMF",itemId,i+1,suffix,toByteArray(imgFiles.get(i)));
        		if(result.getStatusCode() != 0)
        			return false;
        	}
        	return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
		return false;
	}

    /**
     * 保存用户上传的文件
     * @param imgFiles
     * @param itemId    参考说明文档
     * @return
     */
	public boolean saveUploadMultipartFile(List<MultipartFile> imgFiles, String itemId)  {
		Client client = new Client();
        client.connect();
        try {
        	// upload("JYMF",参考文档,ext,文件二进制)
        	// upload(userName,itemId,fileOrder,ext,fileContent)
        	for(int i=0;i<imgFiles.size();i++){
        		if(imgFiles.get(i).isEmpty()){
    				continue;
    			}
        		String fileName = imgFiles.get(i).getOriginalFilename();
        		String suffix=fileName.substring(fileName.lastIndexOf(".")+1);
        		UploadDownJson result = client.upload("JYMF",itemId,i+1,suffix,imgFiles.get(i).getBytes());
        		if(result.getStatusCode() != 0)
        			return false;
        	}
        	return false;
        	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
		return false;
	}
	
    /**
     * 将字符串上传（JSON）
     * @param str
     * @param itemId
     * @param ext
     * @return
     */
	public Boolean saveUploadFile(String str,String itemId, String ext){
		Client client = new Client();
        client.connect();
        try {
        	// upload("JYMF",参考文档,ext,文件二进制)
        	// upload(userName,itemId,fileOrder,ext,fileContent)
        	UploadDownJson result = client.upload("JYMF",itemId,1,ext,str.getBytes("UTF-8"));
        	if(result.getStatusCode() == 0)
    			return true;
    		else
    			return false;
        	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
		return false;
    }
	
    /**
     * 保存用户上传的文件,单个文件
     * @param imgFiles
     * @param itemId    参考说明文档
     * @return
     */
	public boolean saveUploadFile(File file, String itemId, int no)  {
		Client client = new Client();
        client.connect();
        try {
        	// upload("JYMF",参考文档,ext,文件二进制)
        	// upload(userName,itemId,fileOrder,ext,fileContent)
    		String fileName = file.getName();
    		String suffix=fileName.substring(fileName.lastIndexOf(".")+1);
    		UploadDownJson result = client.upload("JYMF",itemId,no,suffix,toByteArray(file));
    		if(result.getStatusCode() == 0)
    			return true;
    		else
    			return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
		return false;
	}
	
	/**
	 * 将文件转换为二进制格式
	 * @param file
	 * @return
	 * @throws IOException
	 */
    private static byte[] toByteArray(File file) throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }


	public Map<Integer,String> readImgFile(String itemId) {
		Client client = new Client();
        client.connect();
        try {
        	// download("JYMF",参考文档)
        	// download(userName,itemId)
        	AbstractJson[] result = client.download("JYMF", itemId);
            DownloadDownJson code = (DownloadDownJson)result[0];
            if(code.getStatusCode()!=0)
            	return null;
            
            RespContentJson content = (RespContentJson)result[1];
            return content.getUrls();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
	
		return null;
	}
	
	public String readJsonFile(String itemId) {
		Client client = new Client();
        client.connect();
        try {
        	// download("JYMF",参考文档)
        	// download(userName,itemId)
        	AbstractJson[] result = client.download("JYMF", itemId);
            DownloadDownJson code = (DownloadDownJson)result[0];
            if(code.getStatusCode()!=0)
            	return null;
            
            RespContentJson content = (RespContentJson)result[1];
            if(content.getUrls().isEmpty())
            	return "";
            
            String fileName = String.format("%s%s",sysConfig.getFileServiceUrl(),content.getUrls().get(1));
            String jsonStr = new String(download(fileName),"UTF-8");
            
            return jsonStr;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
	
		return null;
	}
	
	/**
	 * 读取
	 * @param file
	 * @return
	 */
	public String readFile(File file){
	    if(!file.exists()){
	    	return "";
	    }
	    BufferedReader reader = null;
	    StringBuilder laststr = new StringBuilder();
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        //一次读入一行，直到读入null为文件结束
	        
	        while ((tempString = reader.readLine()) != null) {
	        	laststr.append(new String(tempString.getBytes(),"UTF-8") + "\n");
	        }
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {}
	        }
	    }
	    return laststr.toString();
	}
	
	/**
	 * 读取
	 * @param fileName
	 * @return
	 */
	public String readFile(String fileName){
	    File file = new File(fileName);
	    if(!file.exists()){
	    	return "";
	    }
	    BufferedReader reader = null;
	    String laststr = "";
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        //一次读入一行，直到读入null为文件结束
	        while ((tempString = reader.readLine()) != null) {
	            laststr = laststr + new String(tempString.getBytes(),"UTF-8");
	        }
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {}
	        }
	    }
	    return laststr;
	}
	
	/**
	 * 获取制定路径下的文件
	 * @param path
	 * @param maxCnt 最多获取文件的数量
	 * @return
	 */
	public List<ImgFile> getFiles(Map<Integer,String> map,int maxCnt){
		
		List<ImgFile> files = new ArrayList<ImgFile>();
		for(int i=0;i<maxCnt;i++){
			ImgFile imgFile = new ImgFile();
			
			imgFile.setFileName("");
			imgFile.setNo(String.valueOf(i));
		    files.add(imgFile);
		}
		if(null== map || map.isEmpty())
			return files;
		
		for (Map.Entry<Integer,String> entry : map.entrySet()){
		    if(entry.getKey() > maxCnt)
		    	continue;
		    
			String fileName = String.format("%s%s",sysConfig.getFileServiceUrl(),entry.getValue());
			files.get(entry.getKey()-1).setFileName(fileName);
		}
		
		return files;
	}
	
	/**
	 * 获取知道URL的文件
	 * @param urlString
	 * @return
	 * @throws Exception
	 */
	public byte[] download(String urlString) throws Exception {
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        byte[] bs = new byte[1024];
        int len;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        os.close();
        is.close();
        return os.toByteArray();
    }

    /**
     * 删除文件
     * @param itemId
     * @param fileOrder
     * @return
     */
	public boolean deleteFile(String itemId,int fileOrder){
		Client client = new Client();
        client.connect();
        try {
    		//DeleteDownJson delete(String userName,String itemId,int fileOrder)
        	DeleteDownJson  result = client.delete("JYMF",itemId,fileOrder);
    		if(result.getStatusCode() == 0)
    			return true;
    		else
    			return false;
        	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
        return false;
	}

	public boolean saveCopyFile(String fileUrlName, String itemId, int no) {
		Client client = new Client();
        client.connect();
        try {
        	// upload("JYMF",参考文档,ext,文件二进制)
        	// upload(userName,itemId,fileOrder,ext,fileContent)

    		String suffix=fileUrlName.substring(fileUrlName.lastIndexOf(".") + 1);
    		UploadDownJson result = client.upload("JYMF",itemId,no,suffix,download(fileUrlName));
    		if(result.getStatusCode() == 0)
    			return true;
    		else
    			return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
		return false;
	}
	
	/**
	 * 获取制定路径下的所有文件
	 * @param path
	 * @return
	 */
	public List<ImgFile> getFiles(Map<Integer,String> map){
		
		List<ImgFile> files = new ArrayList<ImgFile>();

		if(null== map || map.isEmpty())
			return null;
		int cnt=0;
		for (Map.Entry<Integer,String> entry : map.entrySet()){
			
			ImgFile imgFile = new ImgFile();
			String fileName = String.format("%s%s",sysConfig.getFileServiceUrl(),entry.getValue());
			imgFile.setFileName(fileName);
			imgFile.setNo(String.valueOf(cnt));
		    files.add(imgFile);
		    cnt++;
		}
		
		return files;
	}
}
