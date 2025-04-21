package com.documentacion.proyecto.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.documentacion.proyecto.dto.ApiDataDTO;
import com.documentacion.proyecto.dto.ModuloAvanzadoDTO;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ModuloAvanzadoController {
    @GetMapping("/formulario-modulo-avanzado")
    public String mostrarFormulario(Model model) {
        // Enviaremos una primera API vacia
        ModuloAvanzadoDTO moduloADTO = new ModuloAvanzadoDTO();
        moduloADTO.getApis().add(new ApiDataDTO());

        model.addAttribute("moduloForm", moduloADTO);
        return "formulario-modulo-avanzado";
    }

    @PostMapping("/procesar-modulo-avanzado")
    public String procesarFormulario(
            @ModelAttribute ModuloAvanzadoDTO moduloADTO,
            Model model) {

        System.out.println(moduloADTO.toString());
        System.out.println(moduloADTO.getApis());
        model.addAttribute("modulo", moduloADTO);
        return "resultadoAvanzado";
    }

    // @PostMapping("/generar-pdf")
    // public String generaPdf(@ModelAttribute ModuloAvanzadoDTO moduloADTO) {
    //     System.out.println("Datos: " + moduloADTO.toString());
    //     System.out.println(moduloADTO.getApis());
    //     return "redirect:/formulario-modulo-avanzado";
    // }

    @PostMapping("/generar-pdf")
    public ResponseEntity<byte[]> generaPdf(@ModelAttribute ModuloAvanzadoDTO moduloADTO) {
        try {
            System.out.println(moduloADTO.toString());
            System.out.println("1. Iniciando generación de reporte...");

            // Cargar reporte principal
            System.out.println("2. Cargando reporte principal...");
            InputStream principalStream = getClass().getResourceAsStream("/reportes/documento.jasper");
            if (principalStream == null) {
                System.err.println(
                        "ERROR: No se pudo cargar el reporte principal. Verifica la ruta: /reportes/documento.jasper");
                throw new FileNotFoundException("Reporte principal no encontrado");
            }
            System.out.println("✓ Reporte principal cargado correctamente");

            // Preparar parámetros
            System.out.println("3. Preparando parámetros...");
            System.out.println("desarrollador: " + moduloADTO.getDesarrollador());
            System.out.println("nombreModulo: " + moduloADTO.getNombreModulo());
            System.out.println("rutaModulo: " + moduloADTO.getRutaModulo());

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("desarrollador", moduloADTO.getDesarrollador());
            parameters.put("nombreModulo", moduloADTO.getNombreModulo());
            parameters.put("rutaModulo", moduloADTO.getRutaModulo());
            parameters.put("javascript", moduloADTO.getJavascript());
            parameters.put("jsp", moduloADTO.getJsp());
            parameters.put("pojo", moduloADTO.getPojo());
            parameters.put("servicio", moduloADTO.getServicio());
            parameters.put("repositorio", moduloADTO.getRepositorio());
            parameters.put("clasesUtiliza", moduloADTO.getClasesUtiliza());
            parameters.put("controladores", moduloADTO.getControlador());
            System.out.println("✓ Parámetros básicos configurados");

            // Configurar datasource para subreportes
            System.out.println("4. Preparando datasource para APIs...");
            System.out.println("Cantidad de APIs encontradas: " + moduloADTO.getApis().size());
            if (moduloADTO.getApis().isEmpty()) {
                System.out.println("ADVERTENCIA: La lista de APIs está vacía");
            }

            JRBeanCollectionDataSource apisDataSource = new JRBeanCollectionDataSource(moduloADTO.getApis());
            parameters.put("apisDataSource", apisDataSource);
            System.out.println("✓ Datasource de APIs configurado");

            // Cargar subreporte
            System.out.println("5. Cargando subreporte...");
            InputStream subStream = getClass().getResourceAsStream("/reportes/subreporteApi.jasper");
            if (subStream == null) {
                System.err.println(
                        "ERROR: No se pudo cargar el subreporte. Verifica la ruta: /reportes/subreporteApi.jasper");
                throw new FileNotFoundException("Subreporte no encontrado");
            }
            parameters.put("subreporteApi", subStream);
            System.out.println("✓ Subreporte cargado correctamente");

            // Generar reporte
            System.out.println("6. Generando reporte...");
            JasperPrint jasperPrint = JasperFillManager.fillReport(principalStream, parameters,
                    new JREmptyDataSource());
            System.out.println("✓ Reporte generado en memoria");

            // Exportar a PDF
            System.out.println("7. Exportando a PDF...");
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
            System.out.println("✓ PDF generado (" + pdfBytes.length + " bytes)");

            // Cerrar streams
            System.out.println("8. Cerrando recursos...");
            principalStream.close();
            subStream.close();
            System.out.println("✓ Recursos liberados");

            System.out.println("9. Enviando respuesta...");
            return ResponseEntity.ok()
                    .header("Content-Type", "application/pdf")
                    .header("Content-Disposition", "attachment; filename=documentacion_modulo.pdf")
                    .body(pdfBytes);

        } catch (JRException e) {
            System.err.println("ERROR en JasperReports: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al generar el reporte", e);
        } catch (IOException e) {
            System.err.println("ERROR de IO: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al leer/escribir recursos", e);
        } catch (Exception e) {
            System.err.println("ERROR inesperado: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error general", e);
        }
    }
}
