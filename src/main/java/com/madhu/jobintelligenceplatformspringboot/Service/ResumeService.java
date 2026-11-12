package com.madhu.jobintelligenceplatformspringboot.Service;

import com.madhu.jobintelligenceplatformspringboot.Exception.InvalidResumeException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class ResumeService {
    public String extractText(MultipartFile file) throws IOException {
        String text;
        if(file.isEmpty()){
            throw new InvalidResumeException("File is Empty");
        }

        byte[] bytes = file.getBytes();

        try(PDDocument document = Loader.loadPDF(bytes)){
            PDFTextStripper stripper = new PDFTextStripper();
            text = stripper.getText(document);
        }catch(IOException e){
            throw new InvalidResumeException("Invalid file format (Only PDF is allowed)");
        }

        if(text.isBlank()){
            throw new InvalidResumeException("Not in readable format");
        }

        return "Extracted " + text.length() + " characters";
    }
}
