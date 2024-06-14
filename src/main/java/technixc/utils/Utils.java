package technixc.utils;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.RPr;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {
    public static Set<String> getFontsFromDocument(WordprocessingMLPackage wordMLPackage) {
        Set<String> fonts = new HashSet<>();
        List<Object> texts = wordMLPackage.getMainDocumentPart().getJaxbElement().getBody().getEGBlockLevelElts();
        for (Object text : texts) {
            if (text instanceof P paragraph) {
                List<Object> runs = paragraph.getContent();
                for (Object run : runs) {
                    if (run instanceof R r) {
                        RPr rPr = r.getRPr();
                        if (rPr != null) {
                            RFonts rFonts = rPr.getRFonts();
                            if (rFonts != null) {
                                if (rFonts.getAscii() != null) {
                                    fonts.add(rFonts.getAscii());
                                }
                                if (rFonts.getHAnsi() != null) {
                                    fonts.add(rFonts.getHAnsi());
                                }
                                if (rFonts.getCs() != null) {
                                    fonts.add(rFonts.getCs());
                                }
                                if (rFonts.getEastAsia() != null) {
                                    fonts.add(rFonts.getEastAsia());
                                }
                            }
                        }
                    }
                }
            }
        }
        return fonts;
    }

}
