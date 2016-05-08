package org.jymf.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageCut {

	// 图片路径
	private String compath;
	// 保存路径
	private String savapath;
	// x坐标
	private int x;
	// y坐标
	private int y;
	// 高度
	private int heigth;
	// 宽度
	private int width;

	public String getCompath() {
		return compath;
	}

	public void setCompath(String compath) {
		this.compath = compath;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public String getSavapath() {
		return savapath;
	}

	public void setSavapath(String savapath) {
		this.savapath = savapath;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/****
	 * 构造函数
	 * 
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param heigth
	 *            高度
	 * @param width
	 *            宽度
	 */
	public ImageCut(int x, int y, int heigth, int width) {
		this.x = x;
		this.y = y;
		this.heigth = heigth;
		this.width = width;
	}

	// 截取方法
	public void cut(String suffix) throws Exception {
		
		String pic_suffix=suffix.substring(1);
		// 文件输入流
		FileInputStream fis = null;
		// 图片输入流
		ImageInputStream iis = null;
		
			// 读取图片
			fis = new FileInputStream(compath);
			/***
			 * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。
			 * 参数：formatName - 包含非正式格式名称 . （例如 "jpeg" 或 "tiff"）等 。
			 */
			Iterator<ImageReader> itor = ImageIO.getImageReadersByFormatName(pic_suffix);
			ImageReader red = itor.next();
			// 获取图片流
			iis = ImageIO.createImageInputStream(fis);
			/***
			 * <p>
			 * iis:读取源.true:只向前搜索
			 * </p>
			 * .将它标记为 ‘只向前搜索’。 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 red
			 * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
			 */
			red.setInput(iis, true);
			/***
			 * <p>
			 * 描述如何对流进行解码的类
			 * <p>
			 * .用于指定如何在输入时从 Java Image I/O 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其
			 * ImageReader 实现的 getDefaultReadParam 方法中返回 ImageReadParam 的实例。
			 */
			ImageReadParam param = red.getDefaultReadParam();
			/***
			 * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
			 * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
			 */
			Rectangle rang = new Rectangle(x, y, width, heigth);
			// 提供一个 BufferedImage，将其用作解码像素数据的目标。
			param.setSourceRegion(rang);
			/***
			 * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的
			 * BufferedImage 返回。
			 */
			BufferedImage bi = red.read(0, param);
			// 写入图片（保存图片）
			ImageIO.write(bi, "jpg", new File(savapath));
		
			if (fis != null)
				try {
					// 关闭输入文件流
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (iis != null)
				try {
					// 关闭图片文件输入流
					iis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}


}
