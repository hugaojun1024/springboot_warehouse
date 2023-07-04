package com.zds.springboot.utils;

//https://www.cnblogs.com/lihongjunjava/p/14825273.html
//https://www.cnblogs.com/love314159/articles/16164053.html
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;


public class PDFUtils {
    //生成文件
    public static void generatePdf(String input,String output,Map<String, Object> data) throws DocumentException, IOException {

        //初始化itext
        //设置编码
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        //要修改的模本文件：E:\谷歌下载\templat.pdf
        PdfReader pdfReader = new PdfReader(input);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(output));
        AcroFields form = pdfStamper.getAcroFields();
        form.addSubstitutionFont(baseFont);

        //写入数据
        for (String key : data.keySet()) {
            String value = data.get(key).toString();
            //key对应模板数据域的名称
            form.setField(key, value);
        }
        //设置不可编辑
        pdfStamper.setFormFlattening(true);
        pdfStamper.close();

    }
    //往pdf文件中插入一张图片
    public static void insertImage(String input,String output,String img,String fieldName) throws IOException, DocumentException{
        //初始化itext
        //设置编码
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        //要修改的模本文件：E:\谷歌下载\templat.pdf
        PdfReader pdfReader = new PdfReader(input);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(output));
        AcroFields form = pdfStamper.getAcroFields();
        form.addSubstitutionFont(baseFont);
        //还要将图片添加到指定的key文本域中
        int pageNo = form.getFieldPositions(fieldName).get(0).page;
        Rectangle signRect = form.getFieldPositions(fieldName).get(0).position;
        float x = signRect.getLeft();
        float y = signRect.getBottom();
        //要添加的图片地址 C:\Users\Administrator\Desktop\6.jpeg
        Image image = Image.getInstance(img);
        PdfContentByte under = pdfStamper.getOverContent(pageNo);
        //设置图片大小
        image.scaleAbsolute(signRect.getWidth(), signRect.getHeight());
        //设置图片位置
        image.setAbsolutePosition(x, y);
        under.addImage(image);
        pdfStamper.close();
    }

}
