package cibertec.edu.pe.DSWII_T3_MarcoBegazo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/filespdf")
public class PDFFileUploadController {

    private static final String UPLOAD_DIRECTORY = "./Documentos"; // Directorio de destino para guardar archivos

    @PostMapping
    @PreAuthorize("hasRole('Supervisor')")
    public ResponseEntity<String> uploadPDFFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("El archivo está vacío");
        }

        if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            return ResponseEntity.badRequest().body("El archivo no es un PDF válido");
        }

        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            String filePath = UPLOAD_DIRECTORY + "/" + file.getOriginalFilename();
            File dest = new File(filePath);
            file.transferTo(dest);
            return ResponseEntity.ok("Archivo PDF subido correctamente");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al guardar el archivo PDF");
        }
    }
}
