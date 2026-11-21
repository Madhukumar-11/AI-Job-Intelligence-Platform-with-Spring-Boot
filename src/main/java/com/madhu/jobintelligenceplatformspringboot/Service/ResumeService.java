package com.madhu.jobintelligenceplatformspringboot.Service;

import com.madhu.jobintelligenceplatformspringboot.DTOs.JobMatchResponseDTO;
import com.madhu.jobintelligenceplatformspringboot.Exception.InvalidResumeException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class ResumeService {

    private final JobMatchService jobMatchService;

    private final SkillExtractor skillExtractor;

    public ResumeService(SkillExtractor  skillExtractor, JobMatchService jobMatchService) {
        this.jobMatchService =jobMatchService;
        this.skillExtractor = skillExtractor;
    }

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

        return text;
    }

    public List<JobMatchResponseDTO> analyzeResume(MultipartFile file) throws IOException {
        String text = extractText(file);
        List<String> resumeSkills = skillExtractor.extractSkills(text);

        return jobMatchService.matchAllJobs(resumeSkills);
    }

}
