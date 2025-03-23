package com.nexign.testNexign.util;

import com.nexign.testNexign.model.CDR;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
@Service
public class FileManager {
    public static final String REPORTS_DIR = "reports";
    public void saveCDRToCSV(UUID uuid, String number, List<CDR> list){
        File reportDir = new File(REPORTS_DIR);
        if(!reportDir.exists()){
            reportDir.mkdirs();
        }
        File reportFile = new File(reportDir, number+"_"+uuid+".csv");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {
            for (CDR cdr : list) {
                writer.write(cdr.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
