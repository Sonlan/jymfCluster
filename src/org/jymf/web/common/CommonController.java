package org.jymf.web.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jymf.service.impl.BusinessLogger;
import org.jymf.utils.FileUtils;
import org.jymf.utils.ImageCut;
import org.jymf.utils.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 图片剪裁控制器
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {
	
	@Autowired
	private BusinessLogger businessLogger;
	
	@Autowired
	private SysConfig sysConfig;
	
	/**
	 * 跳转入图片剪裁页面
	 */
	@RequestMapping(value="pic")
	public String showCutPic(){
		return "/common/pic";
	}
	
	/**
	 *通过请求网络地址，得到图片内容 
	 */
	@RequestMapping(value="getPic/{picName}")
	public String getPic(@PathVariable("picName")String picName,HttpServletRequest request,HttpServletResponse response){
		
		String cacheFolder=request.getSession().getServletContext().getRealPath("")+File.separator+"static"+File.separator+"cache_img"+File.separator;
		String suffix=FileUtils.getSuffix(cacheFolder, picName);
		
		String filePath=cacheFolder+picName+suffix;
		File file=new File(filePath);
		
		outPic(response, file);
		return null;
	}
	
	
	//输出文件流
	private void outPic(HttpServletResponse response,File file){
		FileInputStream in;
		OutputStream o;
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
	
	
	/**
	 * 上传图片
	 */
	@RequestMapping(value="upload" , method = RequestMethod.POST)
	public String upload(Model model, HttpServletRequest request){
		//图片命名为 模块_详细/概览_顺序_时间 ，如 company_info_1_time
		String picDesc=request.getParameter("pic_desc");
		String picOrder=request.getParameter("pic_order");
		
		String webRootUrl=request.getSession().getServletContext().getRealPath("");
		String cacheFolder=webRootUrl+"/static/cache_img/";
		String fileName=picDesc+"_"+picOrder+"_"+System.currentTimeMillis();
		
		
		MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
		List<MultipartFile> imgFilesList=multipartHttpServletRequest.getFiles("pic_file");
		if(imgFilesList.size()!=0 && null!=imgFilesList.get(0)){
			String imgName=imgFilesList.get(0).getOriginalFilename();
			
			String fileFullName=fileName+imgName.substring(imgName.lastIndexOf("."));
			File file =new File(cacheFolder+fileFullName);
			try {
				//原始图片保存到缓存路径下
				imgFilesList.get(0).transferTo(file);
				
				//获取图片的尺寸
				BufferedImage bufferImage=ImageIO.read(file);
				int height=bufferImage.getHeight();
				int width=bufferImage.getWidth();
				
				model.addAttribute("height", height);
				model.addAttribute("width", width);
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("pointer", 2);
		model.addAttribute("pic_url", fileName);
		model.addAttribute("pic_order",picOrder);
		return "/common/pic";
	}
	
	
	/**
	 * 开始剪裁
	 */
	@RequestMapping(value="uploadPicSize")
	public String uploadPicSize(Model model, HttpServletRequest request){
		String suffix="";
		//获取根路径
		String webRootUrl=request.getSession().getServletContext().getRealPath("");
		String cacheFolder=webRootUrl+File.separator+"static"+File.separator+"cache_img"+File.separator;
		// 获得原始图名
		String oldPic=request.getParameter("old_pic");
		int x = Integer.parseInt(request.getParameter("x").split("\\.")[0]);
		int y = Integer.parseInt(request.getParameter("y").split("\\.")[0]);
		int heigth = Integer.parseInt(request.getParameter("h").split("\\.")[0]);
		int width = Integer.parseInt(request.getParameter("w").split("\\.")[0]);
		ImageCut imageCut=new ImageCut(x, y, heigth, width);
		
		String afterCutPic=oldPic+"_"+System.currentTimeMillis();
		
		suffix=FileUtils.getSuffix(cacheFolder, oldPic);
		
		try{
		imageCut.setCompath(cacheFolder+oldPic+suffix);
		imageCut.setSavapath(cacheFolder+afterCutPic+suffix);
		imageCut.cut(suffix);
		
		//获取新图尺寸
		BufferedImage bufferImage=ImageIO.read(new File(cacheFolder+afterCutPic+suffix));
		int newPicHeight=bufferImage.getHeight();
		int newPicWidth=bufferImage.getWidth();
		model.addAttribute("height", newPicHeight);
		model.addAttribute("width", newPicWidth);
		
		}catch(IIOException e){
			model.addAttribute("note", "图片格式错误！");
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		//删除原始图
		new File(cacheFolder+oldPic+suffix).delete();
		
		model.addAttribute("pointer", 3);
		model.addAttribute("pic_url",afterCutPic);
		model.addAttribute("pic_order", oldPic.split("_")[2]);
		return "/common/pic";
	}
}
