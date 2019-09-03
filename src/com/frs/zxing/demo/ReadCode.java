package com.frs.zxing.demo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class ReadCode {

	public static void main(String[] args) throws Exception {
		
		MultiFormatReader formatReader = new MultiFormatReader();
		
		File  file = new File("C:/code/img.png");
		BufferedImage image = ImageIO.read(file);
		
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
		
		HashMap hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		
		Result result = formatReader.decode(binaryBitmap, hints);
		System.out.println("结果是: "+result.toString());
		System.out.println("格式是: " +result.getBarcodeFormat() ) ;
		System.out.println("文本信息是: " + result.getText());
	}
}
