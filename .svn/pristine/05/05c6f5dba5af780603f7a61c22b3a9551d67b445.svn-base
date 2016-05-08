package org.jymf.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class CopyOfFileManager {

	/**
	 * 保存用户上传的文件
	 * 
	 * @param imgFile
	 * @param fileName 文件路径（结尾带有/) 
	 * @param type     文件种类
	 * @return 
	 *        True    文件有上传
	 *       False    文件无上传
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public boolean saveUploadFile(List<MultipartFile> imgFiles, String filePath,String type) throws IllegalStateException, IOException {
		// 自动建立文件夹
		FileUtils.mkdir(filePath);
        boolean isUpload = false;
		for(int i=0;i<imgFiles.size();i++){
			if(imgFiles.get(i).isEmpty()){
				continue;
			}

			// path=文件夹（filepath）/类型_序号_时间戳.jpg
			String path=String.format("%s%s_%s_%s.jpg",filePath,type,i,System.currentTimeMillis());
			FileUtils.fileDelete(filePath, String.format("%s_%s", type,i));
			File file = new File(path);
			
			imgFiles.get(i).transferTo(file);
			isUpload = true;
		}
		return isUpload;
	}
	
	/**
	 * 保存用户上传的文件
	 * 
	 * @param imgFile
	 * @param fileName 文件路径（结尾带有/) 
	 * @return 
	 *        True    文件有上传
	 *       False    文件无上传
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public boolean saveXkzFile(List<MultipartFile> imgFiles, String filePath) throws IllegalStateException, IOException {
		// 自动建立文件夹
		FileUtils.mkdir(filePath);
        boolean isUpload = false;
		for(int i=0;i<imgFiles.size();i++){
			if(imgFiles.get(i).isEmpty()){
				continue;
			}
			// path=文件夹（filepath）/类型_序号_时间戳.jpg
			String path=String.format("%sxkz.jpg",filePath);
			File file = new File(path);
			imgFiles.get(i).transferTo(file);
			isUpload = true;
		}
		return isUpload;
	}
	
	/**
	 * 获取制定路径下的文件
	 * @param path
	 * @param fileProperty 文件的基本信息
	 *              公司：id=公司ID&type=company
	 *              产品：id=公司ID&pId=产品ID&type=product
	 * @param maxCnt 最多获取文件的数量
	 * @return
	 */
	public List<ImgFile> getFiles(String path,String fileProperty,int maxCnt){
		File dir = new File(path);
		List<ImgFile> files=new ArrayList<ImgFile>();
		
		//${ctx}/photo?name=${name}&
		if (dir.exists()) {
			
			List<File> file = Arrays.asList(dir.listFiles());
			Collections.sort(file, new Comparator<File>(){
			    @Override
			    public int compare(File o1, File o2) {
			    if(o1.isDirectory() && o2.isFile())
			        return -1;
			    if(o1.isFile() && o2.isDirectory())
			        return 1;
			    return o1.getName().compareTo(o2.getName());
			    }
			});
			
			int cnt =0;
			
			for (int i = 0; i < file.size() && i< maxCnt; i++) {
				ImgFile imgFile;
				String fileName = String.format("/photo?name=%s&%s",file.get(i).getName(),fileProperty);
				
				String names[] = file.get(i).getName().split("_");
				try{
					int no = Integer.valueOf(names[1]);
					
					while(cnt < no){
						imgFile = new ImgFile();
						
						imgFile.setFileName("");
						imgFile.setNo(String.valueOf(cnt));
						cnt++;
					    files.add(imgFile);
					}
				}catch(Exception e){}
				
				imgFile = new ImgFile();
				imgFile.setFileName(fileName);
				imgFile.setNo(String.valueOf(i));
			    files.add(imgFile);
			    cnt++;
			}
		}
		
		for (int i = files.size(); i < maxCnt; i++){
			ImgFile imgFile = new ImgFile();
			imgFile.setFileName("");
			imgFile.setNo(String.valueOf(i));
		    files.add(imgFile);
		}
		return files;
	}
	
	
	/**
	 * 获取制定路径下的所有文件，按照文件名升序排序
	 * @param path
	 * @param fileProperty 文件的基本信息
	 *              公司：id=公司ID&type=company
	 *              产品：id=公司ID&pId=产品ID&type=product
	 * @return
	 */
	public List<ImgFile> getFiles(String path,String fileProperty){
		File dir = new File(path);
		List<ImgFile> files=new ArrayList<ImgFile>();
		
		//${ctx}/photo?name=${name}&
		if (dir.exists()) {
			
			List<File> file = Arrays.asList(dir.listFiles());
			Collections.sort(file, new Comparator<File>(){
			    @Override
			    public int compare(File o1, File o2) {
			    if(o1.isDirectory() && o2.isFile())
			        return -1;
			    if(o1.isFile() && o2.isDirectory())
			        return 1;
			    return o1.getName().compareTo(o2.getName());
			    }
			});
			
			for (int i = 0; i < file.size(); i++) {
				ImgFile imgFile;
				String fileName = String.format("/photo?name=%s&%s",file.get(i).getName(),fileProperty);
				imgFile = new ImgFile();
				imgFile.setFileName(fileName);
				imgFile.setNo(String.valueOf(i));
			    files.add(imgFile);
			}
		}
		
		return files;
	}
	
	/**
	 * 获取制定路径下的所有文件，按照文件名升序排序
	 * @param path
	 * @return
	 */
	public List<String> getFileNames(String path){
		File dir = new File(path);
		List<String> files=new ArrayList<String>();

		if (dir.exists()) {
			
			List<File> file = Arrays.asList(dir.listFiles());
			Collections.sort(file, new Comparator<File>(){
			    @Override
			    public int compare(File o1, File o2) {
			    if(o1.isDirectory() && o2.isFile())
			        return -1;
			    if(o1.isFile() && o2.isDirectory())
			        return 1;
			    return o1.getName().compareTo(o2.getName());
			    }
			});
			
			for (int i = 0; i < file.size(); i++) {
				files.add(file.get(i).getName());
			}
		}
		
		return files;
	}
	
	/**
	 * 将文件内容(str)写到指定的位置
	 * @param str       将要写入的内容
	 * @param path      文件所在路径
	 * @param fileName  文件的名称，带路径
	 * @return true     成功
	 *        false     失败
	 */
	public Boolean saveUploadFile(String str,String path,String fileName){
	    Boolean rst=false;
	    FileUtils.mkdir(path);
	    
        BufferedWriter writer = null;
        File file = new File(fileName);
        //如果文件不存在，则新建一个
        if(!file.exists()){
            try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return rst;
			}
        }
		
		 //写入
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(new String(str.getBytes(),"UTF-8"));
            rst=true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rst;
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
	 * 删除被客户端删除的画面
	 * 
	 * @param imgFile
	 * @param fileName 文件路径（结尾带有/) 
	 * @param type     文件种类
	 * @return 
	 *        True    文件有删除
	 *       False    文件无删除
	 */
	public boolean deleteImgsFile(List<ImgFile> imgs, String filePath,String type){
		boolean isDel = false;
		for(int i=0;i<imgs.size();i++){
			if(!imgs.get(i).getFileName().isEmpty()){
				continue;
			}
			if(FileUtils.fileDelete(filePath, String.format("%s_%s", type,i)))
				isDel=true;
		}
		
		return isDel;
	}
	
	/**
	 * 保存用户上传的文件
	 * @param imgFile
	 * @param filePath
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void saveUploadFile(List<MultipartFile> imgFiles, String filePath) 
			    throws IllegalStateException, IOException {
		
		// 自动建立文件夹
		FileUtils.mkdir(filePath);
		for(MultipartFile imgFile : imgFiles){
			if(null == imgFile || 0 == imgFile.getSize()){
				continue;
			}
			String fileName = imgFile.getOriginalFilename();
			String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
	
			String path=String.format("%s/%s.%s",filePath,System.currentTimeMillis(),prefix);
			File file = new File(path);
			imgFile.transferTo(file);
		}
	}

	public boolean fileExists(String fileName) {
	    File file = new File(fileName);
		return file.exists();
	}
	
	/**
	 * 图片新增时通过本地查找的图片
	 * @author wfj  2015-4-21
	 * @throws InterruptedException 
	 * 
	 * */
	public boolean saveFileFromLocal(List<File> imgFiles, String filePath,String type) throws IllegalStateException, IOException, InterruptedException {
		// 自动建立文件夹
		FileUtils.mkdir(filePath);
		
        boolean isUpload = false;
		for(int i=0;i<imgFiles.size();i++){
			if(!imgFiles.get(i).exists()){
				continue;
			}
			// path=文件夹（filepath）/类型_序号_时间戳.jpg
			String path=String.format("%s%s_%s_%s.jpg",filePath,type,i,System.currentTimeMillis());
			FileUtils.fileDelete(filePath, String.format("%s_%s", type,i));
			
			isUpload = copyFileTo(imgFiles.get(i),path);
		}
		return isUpload;
	}
	
	/**
	 * 复制本地文件到另一目录,复制后删除源文件
	 * @throws IOException 
	 * @author wfj 2014-4-21
	 * */
	public boolean copyFileTo(File file,String destUrl){
		boolean isupdate=false;
		try{
			FileInputStream fis = new FileInputStream(file);
	        BufferedInputStream bufis = new BufferedInputStream(fis);
	        FileOutputStream fos = new FileOutputStream(destUrl);
	        BufferedOutputStream bufos = new BufferedOutputStream(fos);
	 
	        int len = 0;
	        while ((len = bufis.read()) != -1) {
	            bufos.write(len);
	        }
	        
	        bufis.close();
	        bufos.close();
	        file.delete();
	        isupdate=true;
		}catch(IOException e){
			isupdate=false;
			//e.printStackTrace();
		}
		return isupdate;
	}

	/**
	 * 按照路径名删除原始文件
	 * @author wfj 2014-4-21
	 * */	
	public boolean deleteImg(String paths, String type, int no) {
		return FileUtils.fileDelete(paths, String.format("%s_%s", type,no));
	}
	
	/**
	 * 查找指定文件夹下是否包含某文件
	 * @param folder   文件夹绝对路径
	 * 		  fileName 文件名（包括后缀）
	 * @return 包含    = true
	 * 		     不包含 = flase
	 * update by wfj 2015.5.5
	 */
	public boolean isContain(String folder,String fileName){
		boolean flag = false;
		File file = new File(folder+fileName);
		if(file.exists()){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 通过指定路径下载文件
	 *  
	 */
	public void downloadFile(String filePath,String fileName,HttpServletRequest request,
							 HttpServletResponse response) throws Exception{
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
	
		long fileLength = new File(filePath).length();

		response.setContentType("application/x-msdownload;");
		response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"),"ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(filePath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
	
		if (bis != null)
			bis.close();
		if (bos != null)
			bos.close();
	}
	
	
	/**
	 * 复制本地文件到另一目录
	 * @throws IOException 
	 * @author wfj 2014-4-21
	 * */
	public boolean copyFileWithoutDel(File file,String destUrl){
		boolean isupdate=false;
		try{
			FileInputStream fis = new FileInputStream(file);
	        BufferedInputStream bufis = new BufferedInputStream(fis);
	        FileOutputStream fos = new FileOutputStream(destUrl);
	        BufferedOutputStream bufos = new BufferedOutputStream(fos);
	 
	        int len = 0;
	        while ((len = bufis.read()) != -1) {
	            bufos.write(len);
	        }
	        
	        bufis.close();
	        bufos.close();
	        isupdate=true;
		}catch(IOException e){
			isupdate=false;
			e.printStackTrace();
		}
		return isupdate;
	}
	
	/**
	 * 拆解路径//${ctx}/photo?name=${name}&id=${id}
	 * wfj 2015.5.58
	 */
	public List<ImgFile> changeToRealName(List<ImgFile> list){
		List<ImgFile> imgList = new ArrayList<ImgFile>();
		for(int i=0;i<list.size();i++){
			ImgFile imgFile = new ImgFile();
			if(null!=list.get(i).getFileName() && !"".equals(list.get(i).getFileName())){
				String fileName = list.get(i).getFileName();
				String picName = "";
				if(fileName.contains("/")){
					picName = fileName.split("[?]")[1].split("&")[0].split("=")[1];
				}else{
					picName = fileName;
				}
				imgFile.setFileName(picName);
			}else{
				imgFile.setFileName("");
			}
			imgList.add(imgFile);
		}
		return imgList;
	}
	
	/**
	 * 显示图片的方法
	 */
	public void getPic(String picPath,HttpServletResponse response){
		FileInputStream in;
		OutputStream o;
		File file=new File(picPath);
		try {
				in = new FileInputStream(file);
				o = response.getOutputStream(); 
				int l = 0; 
				byte[] buffer = new byte[4096]; 
				while((l = in.read(buffer)) != -1){ 
					o.write(buffer,0,l); 
				} 
				o.flush(); 
				in.close();
				o.close();
			
		} catch (Exception e) {
			//e.printStackTrace();
		} 
	}
}
