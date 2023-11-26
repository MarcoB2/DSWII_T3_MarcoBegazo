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
@RequestMapping("/filesdoc")
public class DOCFileUploadController {

    private static final String UPLOAD_DIRECTORY = "./Documentos";//Aqui puede poner la carpeta que quiera, se guardara ahi

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<String> uploadDOCFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("El archivo está vacío");
        }

        if (!file.getOriginalFilename().toLowerCase().endsWith(".doc")) {
            return ResponseEntity.badRequest().body("El archivo no es un DOC válido");
        }

        if (file.getSize() > 2 * 1024 * 1024) {
            return ResponseEntity.badRequest().body("El tamaño del archivo supera los 2MB permitidos");
        }

        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            String filePath = UPLOAD_DIRECTORY + "/" + file.getOriginalFilename();
            File dest = new File(filePath);
            file.transferTo(dest);
            return ResponseEntity.ok("Archivo DOC subido correctamente");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al guardar el archivo DOC");
        }
    }
}
