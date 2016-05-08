package org.jymf.utils;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

public class CompressUtil {

	/**
	 * 目标文件路径
	 */
	private File zipFile;

	public CompressUtil(String pathName) {
		zipFile = new File(pathName);
	}
	
	/**
	 * @param 
	 * 			fileName = 需要压缩的文件名 如 1.txt
	 * <br>		dest = 压缩到的文件夹
	 */
	public void compressZip(String fileName, String dest) {
		File destDir = new File(dest);
		if (!destDir.exists()){
			FileUtils.mkdir(dest);
		}
		//创建zip工具
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(zipFile);
		
		//创建徐压缩的文件
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(destDir);
		fileSet.setIncludes(fileName); //包括哪些文件或文件夹 eg:zip.setIncludes("*.java");
		//fileSet.setExcludes(...); 排除哪些文件或文件夹
		
		zip.addFileset(fileSet);
		
		zip.execute();
	}
}