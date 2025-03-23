package com.nexign.testNexign.service;

import com.nexign.testNexign.model.CDR;
import com.nexign.testNexign.repository.CDRRepository;
import com.nexign.testNexign.util.FileManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Класс для бизнес-логики связанной с CDR
 */
@Service
public class CDRService {
    private CDRRepository cdrRepository;
    private FileManager fileManager;

    public CDRService(CDRRepository cdrRepository, FileManager fileManager) {
        this.cdrRepository = cdrRepository;
        this.fileManager = fileManager;
    }

    /**
     * Создает .csv файл со всеми CDR по абоненту за промежуток времени
     * @param number номер телефона абонента
     * @param startDate начало временного промежутка
     * @param endDate конец временного промежутка
     * @return ответ на POST запрос
     */
    public ResponseEntity<String> genCDRReport(String number, Instant startDate, Instant endDate) {
        List<CDR> receiverList = cdrRepository.findByReceiverNumberAndTimeInterval(number, startDate, endDate);
        List<CDR> initiatorList = cdrRepository.findByInitiatorNumberAndTimeInterval(number, startDate, endDate);
        UUID uuid = UUID.randomUUID();
        receiverList.addAll(initiatorList);
        try {
            fileManager.saveCDRToCSV(uuid, number, receiverList);
        }catch (RuntimeException e){
            return ResponseEntity.status(507).body("Insufficient Storage");
        }

        return ResponseEntity.ok().body("ok");
    }
}
