package com.frs.zxing.demo;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CreateCode {
	public static void main(String[] args) throws Exception {

		
		int width = 300;
		int height = 300;
		String format = "png";
		String content = "https://github.com/2745635513";
		
		/**
		 * 定义二维码参数
		 */
		HashMap hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hints.put(EncodeHintType.MARGIN, 2);
		
		 try {
			BitMatrix bitMatrix =  new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			
			Path file = new File("C:/code/img.png").toPath();
			MatrixToImageWriter.writeToPath(bitMatrix, format, file);
		} catch (WriterException e) {
			e.printStackTrace();
		}

	
	}
}
