package technixc.converter;

import org.docx4j.Docx4J;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import technixc.utils.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Set;

public class Converter {

    public static byte[] convertWordToPdf(byte[] docBytes, boolean forceCyrillic) {

        try (ByteArrayInputStream docStream = new ByteArrayInputStream(docBytes);
             ByteArrayOutputStream pdfStream = new ByteArrayOutputStream()) {

            // Load the DOCX into WordprocessingMLPackage
            WordprocessingMLPackage wordMLPackage = Docx4J.load(docStream);

            // Set up font mapping
            IdentityPlusMapper fontMapper = new IdentityPlusMapper();
            URL resource = Converter.class.getClassLoader().getResource("arial.ttf");

            if (resource != null && forceCyrillic) {
                PhysicalFonts.addPhysicalFont(resource.toURI());
                fontMapper.put("arial cyr", PhysicalFonts.get("arial cyr"));

                // Map all fonts in the document to Arial
                Set<String> fontsInDocument = Utils.getFontsFromDocument(wordMLPackage);
                for (String fontName : fontsInDocument) {
                    fontMapper.put(fontName, PhysicalFonts.get("arial cyr"));
                }
                wordMLPackage.setFontMapper(fontMapper);
            }

            // Convert DOCX to PDF
            Docx4J.toPDF(wordMLPackage, pdfStream);

            return pdfStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
